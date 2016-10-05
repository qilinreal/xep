package com.ssh.xep.service;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;

import com.ssh.xep.entity.Tool;

public interface ToolService {
	Tool load(Integer id);

	Tool get(Integer id) throws DocumentException;

	List<Tool> findAll() throws DocumentException;

	List<Tool> findAll(Integer userId) throws DocumentException;

	void pessist(Tool entity);

	/**
	 * @param entity
	 *            传入的实体
	 * @return 生成的ID
	 * @throws ParserConfigurationException
	 */
	Integer save(Tool entity) throws ParserConfigurationException;

	void saveOrUpdate(Tool entity) throws ParserConfigurationException;

	void delete(Integer id);

	void flush();
}
