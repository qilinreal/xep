package com.ssh.xep.dao;

import java.util.List;

import com.ssh.xep.entity.Tool;

public interface ToolDao extends GenericDao<Tool, Integer> {
	List<Tool> findAll(Integer userId);
}
