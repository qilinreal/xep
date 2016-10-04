package com.ssh.xep.action;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ssh.xep.entity.FlowBasicInfo;
import com.ssh.xep.service.FlowBasicInfoService;
import com.ssh.xep.util.XML2JSON;

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
		if (id != null) {
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
		Integer userId = (Integer) (ServletActionContext.getRequest().getSession().getAttribute("userId"));
		infos = service.findAll(userId);
		return SUCCESS;
	}

	@Action("detail")
	public String detail() throws DocumentException {
		String id = ServletActionContext.getRequest().getParameter("id");
		LOGGER.info("查看某个流程详细信息： " + id);
		info = service.get(Integer.valueOf(id));
		ServletActionContext.getRequest().setAttribute("bpmn", XML2JSON.xml2JSON(info.getBpmn()));
		return SUCCESS;
	}

	@Action(value = "modify", results = { @Result(name = "error", location = "/WEB-INF/error/no-object.jsp"),
			@Result(name = SUCCESS, location = "/WEB-INF/content/flow/modify.jsp") })
	public String modify() throws DocumentException {
		String id = ServletActionContext.getRequest().getParameter("id");
		LOGGER.info("修改或者创建某个流程： " + id);
		if (id == null) {
			ServletActionContext.getRequest().setAttribute("create", "创建");
			FlowBasicInfo info = new FlowBasicInfo();
			info.setBpmn("");
			info.setFlowNum(0);
			info.setName("NO NAME");
			id = String.valueOf(service.save(info));
		} else {
			ServletActionContext.getRequest().setAttribute("create", "修改");
			FlowBasicInfo info = service.get(Integer.parseInt(id));
			if (info == null) {
				return ERROR;
			}
			ServletActionContext.getRequest().setAttribute("bpmn", XML2JSON.xml2JSON(info.getBpmn()));
			ServletActionContext.getRequest().setAttribute("flowNum", info.getFlowNum());
			ServletActionContext.getRequest().setAttribute("name", info.getName());
			ServletActionContext.getRequest().setAttribute("userId", info.getUserId());
		}
		return SUCCESS;
	}
}
