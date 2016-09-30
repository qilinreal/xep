package com.ssh.xep.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.xep.dao.FlowBasicInfoDao;
import com.ssh.xep.entity.FlowBasicInfo;

@Repository("flowBasicInfoDao")
public class FlowBasicInfoDaoImpl implements FlowBasicInfoDao {
	private SessionFactory sessionFactory;
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public FlowBasicInfo load(Integer id) {
		return (FlowBasicInfo) getSession().load(FlowBasicInfo.class, id);
	}

	public FlowBasicInfo get(Integer id) {
		return (FlowBasicInfo) getSession().get(FlowBasicInfo.class, id);
	}

	public List<FlowBasicInfo> findAll() {
		List<FlowBasicInfo> infos = getSession().createQuery("from flowBasicInfo").list();
		return infos;
	}

	public void persist(FlowBasicInfo entity) {

	}

	public Integer save(FlowBasicInfo entity) {
		return null;
	}

	public void saveOrUpdate(FlowBasicInfo entity) {

	}

	public void delete(Integer id) {

	}

	public void flush() {

	}

}
