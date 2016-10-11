package com.ssh.xep.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.xep.dao.FlowBasicInfoDao;
import com.ssh.xep.dao.ToolsDao;
import com.ssh.xep.entity.FlowBasicInfo;
import com.ssh.xep.service.FlowBasicInfoService;
import com.ssh.xep.util.JSON2XML;
import com.ssh.xep.util.XML2JSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("flowBasicInfoService")
public class FlowBasicInfoServiceImpl implements FlowBasicInfoService {
	@Autowired
	private FlowBasicInfoDao dao;
	@Autowired
	private ToolsDao toolDao;
	@Autowired
	private ToolTypeDao toolTypeDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private DirectoryDao dirDao;

	@Deprecated
	public FlowBasicInfo load(Integer id) {
		return dao.load(id);
	}

	public FlowBasicInfo get(Integer id) throws DocumentException {
		FlowBasicInfo info = dao.get(id);
		if(info != null) {
			info.setBpmn(XML2JSON.xml2JSON(info.getBpmn()));
		}
		return info;
	}

	public List<FlowBasicInfo> findAll() throws DocumentException {
		List<FlowBasicInfo> infos = dao.findAll();
		if(infos != null) {
			for (FlowBasicInfo info : infos) {
				info.setBpmn(XML2JSON.xml2JSON(info.getBpmn()));
			}
		}
		return infos;
	}

	public List<FlowBasicInfo> findAll(Integer userId) throws DocumentException {
		List<FlowBasicInfo> infos = dao.findAll(userId);
		if(infos != null) {
			for (FlowBasicInfo info : infos) {
				info.setBpmn(XML2JSON.xml2JSON(info.getBpmn()));
			}
		}
		return infos;
	}

	public void pessist(FlowBasicInfo entity) {
		dao.persist(entity);
	}

	private String fixPath(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		JSONArray infos = obj.getJSONArray("Obj");
		fixPath2(infos);
		return infos.toString();
	}
	private void fixPath2(JSONArray infos) {
		Iterator<JSONObject> it = infos.iterator();
		while(it.hasNext()) {
			JSONObject obj = it.next();
			fixPath3(obj.getJSONArray("tool-info"));
		}
	}
	private void fixPath3(JSONArray toolInfos) {
		Iterator<JSONObject> it = toolInfos.iterator();
		while(it.hasNext()) {
			JSONObject obj = it.next();
			if(obj.getString("type").startsWith("db") == false) {
				continue;
			}
			String id = obj.getString("value");
		}
	}
	
	public Integer save(FlowBasicInfo entity) throws ParserConfigurationException {
		if(entity != null) {
			if (entity.getBpmn().startsWith("{")) {
				String bpmn = fixPath(entity.getBpmn());
				entity.setBpmn(JSON2XML.json2XML(entity.getId(), entity.getName(), bpmn));
			}
		}
		return dao.save(entity);
	}

	public void saveOrUpdate(FlowBasicInfo entity) throws ParserConfigurationException {
		if(entity != null) {
			if (entity.getBpmn().startsWith("{")) {
				String bpmn = fixPath(entity.getBpmn());
				entity.setBpmn(JSON2XML.json2XML(entity.getId(), entity.getName(), bpmn));
			}
		}
		dao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void flush() {
		dao.flush();
	}

}
