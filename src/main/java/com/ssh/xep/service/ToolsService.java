package com.ssh.xep.service;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;

import com.ssh.xep.entity.Tools;

public interface ToolsService {
	Tools load(Integer id);

	Tools get(Integer id) throws DocumentException;

	List<Tools> findAll() throws DocumentException;

	List<Tools> findAll(Integer userId) throws DocumentException;

	void pessist(Tools entity);

	/**
	 * @param entity
	 *            传入的实体
	 * @return 生成的ID
	 * @throws ParserConfigurationException
	 */
	Integer save(Tools entity) throws ParserConfigurationException;

	void saveOrUpdate(Tools entity) throws ParserConfigurationException;

	void delete(Integer id);

	void flush();
}
