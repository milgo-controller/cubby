package com.milgo.cubby.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milgo.cubby.dao.RoleDao;
import com.milgo.cubby.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao{

	public static final String ROLE_ADMIN = null;
	public static final String ROLE_MODERATOR = null;
	public static final String ROLE_ANONYMOUS = null;
	
	@Autowired
	public SessionFactory sessionFactory;
	
	public RoleDaoImpl(){
		System.out.println("you can take role names here");
	}
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void addRole(Role role) {
		getCurrentSession().save(role);
	}

	@Transactional
	public void removeRole(Role role) {
		getCurrentSession().delete(role);		
	}

	@Transactional
	public Role getRoleByName(String name) {
		List<?> list = sessionFactory.getCurrentSession()
				.createCriteria(Role.class)
				.add(Restrictions.eq("roleName", name)).list();
		if(!list.isEmpty())return (Role)list.get(0);
		return null;
	}
	
	@Transactional
	public List<?> getAllRoles() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Role.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
