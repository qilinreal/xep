package com.ssh.xep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.xep.dao.FlowBasicInfoDao;
import com.ssh.xep.entity.FlowBasicInfo;
import com.ssh.xep.service.FlowBasicInfoService;

@Service("flowBasicInfoService")
public class FlowBasicInfoServiceImpl implements FlowBasicInfoService {
	@Autowired
	private FlowBasicInfoDao dao;

	public FlowBasicInfo load(Integer id) {
		return dao.load(id);
	}

	public FlowBasicInfo get(Integer id) {
		return dao.get(id);
	}

	public List<FlowBasicInfo> findAll() {
		return dao.findAll();
	}
	
	public List<FlowBasicInfo> findAll(Integer userId) {
		return dao.findAll(userId);
	}

	public void pessist(FlowBasicInfo entity) {
		dao.persist(entity);
	}

	public Integer save(FlowBasicInfo entity) {
		return dao.save(entity);
	}

	public void saveOrUpdate(FlowBasicInfo entity) {
		dao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void flush() {
		dao.flush();
	}

}
