package com.milgo.cubby.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milgo.cubby.dao.UserDao;
import com.milgo.cubby.model.User;
import com.milgo.cubby.model.UserTrainings;

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
	public void modifyUser(User user) {
		getCurrentSession().update(user);	
	}
	
	@Transactional
	public void removeUser(User user) {

		//System.out.println("lalala");
		Set<UserTrainings> trainSet = user.getUserTrainings();
		System.out.println(trainSet.size());
		for(UserTrainings t: trainSet){
			System.out.println("gogogo");
			getCurrentSession().delete(t);
		}
		getCurrentSession().delete(user);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly=false)
	public User getUserByLogin(String login){
		return (User)sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("login", login)).uniqueResult();
		
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
