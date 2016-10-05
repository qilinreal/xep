package com.ssh.xep.service.impl;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.xep.dao.ToolDao;
import com.ssh.xep.entity.Tool;
import com.ssh.xep.service.ToolService;

@Service("jobInfoService")
public class ToolServiceImpl implements ToolService {
	@Autowired
	private ToolDao dao;

	@Deprecated
	public Tool load(Integer id) {
		return dao.load(id);
	}

	public Tool get(Integer id) throws DocumentException {
		return dao.get(id);
	}

	public List<Tool> findAll() throws DocumentException {
		return dao.findAll();
	}

	public List<Tool> findAll(Integer userId) throws DocumentException {
		return dao.findAll(userId);
	}

	public void pessist(Tool entity) {
		dao.persist(entity);
	}

	public Integer save(Tool entity) throws ParserConfigurationException {
		return dao.save(entity);
	}

	public void saveOrUpdate(Tool entity) throws ParserConfigurationException {
		dao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void flush() {
		dao.flush();
	}

}
