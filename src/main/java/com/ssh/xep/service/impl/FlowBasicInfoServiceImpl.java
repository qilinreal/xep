package com.ssh.xep.service.impl;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.xep.dao.FlowBasicInfoDao;
import com.ssh.xep.entity.FlowBasicInfo;
import com.ssh.xep.service.FlowBasicInfoService;
import com.ssh.xep.util.JSON2XML;
import com.ssh.xep.util.XML2JSON;

@Service("flowBasicInfoService")
public class FlowBasicInfoServiceImpl implements FlowBasicInfoService {
	@Autowired
	private FlowBasicInfoDao dao;

	@Deprecated
	public FlowBasicInfo load(Integer id) {
		return dao.load(id);
	}

	public FlowBasicInfo get(Integer id) throws DocumentException {
		FlowBasicInfo info = dao.get(id);
		info.setBpmn(XML2JSON.xml2JSON(info.getBpmn()));
		return info;
	}

	public List<FlowBasicInfo> findAll() throws DocumentException {
		List<FlowBasicInfo> infos = dao.findAll();
		for (FlowBasicInfo info : infos) {
			info.setBpmn(XML2JSON.xml2JSON(info.getBpmn()));
		}
		return infos;
	}

	public List<FlowBasicInfo> findAll(Integer userId) throws DocumentException {
		List<FlowBasicInfo> infos = dao.findAll(userId);
		for (FlowBasicInfo info : infos) {
			info.setBpmn(XML2JSON.xml2JSON(info.getBpmn()));
		}
		return infos;
	}

	public void pessist(FlowBasicInfo entity) {
		dao.persist(entity);
	}

	public Integer save(FlowBasicInfo entity) throws ParserConfigurationException {
		if (entity.getBpmn().startsWith("{")) {
			entity.setBpmn(JSON2XML.json2XML(entity.getId(), entity.getName(), entity.getBpmn()));
		}
		return dao.save(entity);
	}

	public void saveOrUpdate(FlowBasicInfo entity) throws ParserConfigurationException {
		if (entity.getBpmn().startsWith("{")) {
			System.out.println("----------------------------------------");
			entity.setBpmn(JSON2XML.json2XML(entity.getId(), entity.getName(), entity.getBpmn()));
		}
		System.out.println("===========================================");
		dao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void flush() {
		dao.flush();
	}

}
