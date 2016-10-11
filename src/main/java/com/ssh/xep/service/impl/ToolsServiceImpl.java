package com.ssh.xep.service.impl;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.xep.dao.ToolsDao;
import com.ssh.xep.entity.Tools;
import com.ssh.xep.service.ToolsService;

@Service("toolsService")
public class ToolsServiceImpl implements ToolsService {
	@Autowired
	private ToolsDao dao;

	@Deprecated
	public Tools load(Integer id) {
		return dao.load(id);
	}

	public Tools get(Integer id) throws DocumentException {
		return dao.get(id);
	}

	public List<Tools> findAll() throws DocumentException {
		return dao.findAll();
	}

	public List<Tools> findAll(Integer userId) throws DocumentException {
		return dao.findAll(userId);
	}

	public void pessist(Tools entity) {
		dao.persist(entity);
	}

	public Integer save(Tools entity) throws ParserConfigurationException {
		return dao.save(entity);
	}

	public void saveOrUpdate(Tools entity) throws ParserConfigurationException {
		dao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void flush() {
		dao.flush();
	}

}
