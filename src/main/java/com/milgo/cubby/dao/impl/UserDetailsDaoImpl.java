package com.milgo.cubby.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milgo.cubby.dao.UserDetailsDao;
import com.milgo.cubby.model.UserDetails;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao{

	@Autowired
	public SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void addUser(UserDetails userDetails) {
		getCurrentSession().save(userDetails);	
	}
	
	@Transactional
	public void removeUser(UserDetails userDetails) {
		getCurrentSession().delete(userDetails);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public UserDetails getUserByLogin(String login){
		
		List<?> list = sessionFactory.getCurrentSession()
				.createCriteria(UserDetails.class)
				.add(Restrictions.eq("login", login)).list();
		if(!list.isEmpty())return (UserDetails)list.get(0);
		return null;
	}
	
	
}
