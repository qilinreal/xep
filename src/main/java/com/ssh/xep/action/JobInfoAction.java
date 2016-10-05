package com.ssh.xep.action;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ssh.xep.entity.JobInfo;
import com.ssh.xep.service.JobInfoService;

@Namespace("/job")
public class JobInfoAction extends ActionSupport implements ModelDriven<JobInfo>, Preparable {

	private static final long serialVersionUID = -7988746546869953899L;

	private static final Logger LOGGER = Logger.getLogger(JobInfoAction.class);

	private Integer id;
	private JobInfo info;
	private List<JobInfo> infos;

	@Autowired
	private JobInfoService service;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JobInfo getInfo() {
		return info;
	}

	public void setInfo(JobInfo info) {
		this.info = info;
	}

	public List<JobInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<JobInfo> infos) {
		this.infos = infos;
	}

	public void prepare() throws Exception {
	}

	public JobInfo getModel() {
		if (id != null) {
			try {
				info = service.get(id);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			info = new JobInfo();
		}
		return info;
	}

	@Override
	@Action("view")
	public String execute() throws Exception {
		LOGGER.info("查询所有流程");
		Integer userId = (Integer) (ServletActionContext.getRequest().getSession().getAttribute("userId"));
		infos = service.findAll(userId);

		return SUCCESS;
	}
}
