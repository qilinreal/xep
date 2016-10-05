package com.ssh.xep.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssh.xep.dao.ToolDao;
import com.ssh.xep.entity.Tool;

@Repository("toolDao")
public class ToolDaoImpl implements ToolDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Tool load(Integer id) {
		return (Tool) getSession().load(Tool.class, id);
	}

	public Tool get(Integer id) {
		return (Tool) getSession().get(Tool.class, id);
	}

	public List<Tool> findAll() {
		List<Tool> infos = getSession().createQuery("from Tool").list();
		return infos;
	}

	public void persist(Tool entity) {
		getSession().persist(entity);
	}

	public Integer save(Tool entity) {
		return (Integer) getSession().save(entity);
	}

	public void saveOrUpdate(Tool entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		Tool entity = load(id);
		getSession().delete(entity);
	}

	public void flush() {
		getSession().flush();
	}

	public List<Tool> findAll(Integer userId) {
		List<Tool> infos = getSession().createQuery("from FlowBasicInfo where userId=?0").setInteger("0", userId)
				.list();
		return infos;
	}

}
