package com.ssh.xep.service;

import java.util.List;

import com.ssh.xep.entity.FlowBasicInfo;

public interface FlowBasicInfoService {
	FlowBasicInfo load(Integer id);

	FlowBasicInfo get(Integer id);

	List<FlowBasicInfo> findAll();

	void pessist(FlowBasicInfo entity);

	Integer save(FlowBasicInfo entity);

	void saveOrUpdate(FlowBasicInfo entity);

	void delete(Integer id);

	void flush();
}
