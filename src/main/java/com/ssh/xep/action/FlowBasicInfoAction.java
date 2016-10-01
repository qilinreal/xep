package com.ssh.xep.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ssh.xep.entity.FlowBasicInfo;
import com.ssh.xep.service.FlowBasicInfoService;

@Namespace("/flow")
public class FlowBasicInfoAction extends ActionSupport implements ModelDriven<FlowBasicInfo>, Preparable {

	private static final long serialVersionUID = -7988746546869953899L;

	private static final Logger LOGGER = Logger.getLogger(FlowBasicInfoAction.class);

	private Integer id;
	private FlowBasicInfo info;
	private List<FlowBasicInfo> infos;
	
	@Autowired
	private FlowBasicInfoService service;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FlowBasicInfo getInfo() {
		return info;
	}

	public void setInfo(FlowBasicInfo info) {
		this.info = info;
	}

	public List<FlowBasicInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<FlowBasicInfo> infos) {
		this.infos = infos;
	}

	public void prepare() throws Exception {
	}

	public FlowBasicInfo getModel() {
		if(id != null) {
			info = service.get(id);
		} else {
			info = new FlowBasicInfo();
		}
		return info;
	}

	@Override
	@Action("view")
	public String execute() throws Exception {
		LOGGER.info("查询所有流程");
		infos = service.findAll();
		return SUCCESS;
	}

	@Action("detail")
	public String detail() {
		String id = ServletActionContext.getRequest().getParameter("id");
		LOGGER.info("查看某个流程详细信息： "+id);
		info = service.get(Integer.valueOf(id));
		return SUCCESS;
	}
}
