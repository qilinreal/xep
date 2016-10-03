package com.ssh.xep.service;

import java.util.List;

import com.ssh.xep.entity.FlowBasicInfo;

public interface FlowBasicInfoService {
	FlowBasicInfo load(Integer id);

	FlowBasicInfo get(Integer id);

	List<FlowBasicInfo> findAll();
	
	List<FlowBasicInfo> findAll(Integer userId);

	void pessist(FlowBasicInfo entity);

	/**
	 * @param entity 传入的实体
	 * @return 生成的ID
	 */
	Integer save(FlowBasicInfo entity);

	void saveOrUpdate(FlowBasicInfo entity);

	void delete(Integer id);

	void flush();
}
