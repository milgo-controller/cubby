package com.milgo.cubby.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milgo.cubby.dao.UserDao;
import com.milgo.cubby.model.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	public SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void addUser(User user) {
		getCurrentSession().save(user);	
	}
	
	@Transactional
	public void removeUser(User user) {
		getCurrentSession().delete(user);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public User getUserByLogin(String login){
		
		List<?> list = sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("login", login)).list();
		if(!list.isEmpty())return (User)list.get(0);
		return null;
	}

	@Transactional
	public boolean isLoginUsed(String login) {
		User user = getUserByLogin(login);
		return (user!=null);
	}

	@Transactional
	public List<?> getAllUsers() {
		return sessionFactory.getCurrentSession()
				.createCriteria(User.class).list();
	}
	
}
