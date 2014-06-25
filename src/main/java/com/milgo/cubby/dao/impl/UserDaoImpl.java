package com.milgo.cubby.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milgo.cubby.dao.UserDao;
import com.milgo.cubby.model.User;
import com.milgo.cubby.model.UserTraining;

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
		Set<UserTraining> trainSet = user.getUserTrainings();
		System.out.println(trainSet.size());
		for(UserTraining t: trainSet){
			//System.out.println("gogogo");
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
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@Transactional
	public void removeUserTraining(User user, UserTraining userTraining) {
		Set<UserTraining> trainSet = user.getUserTrainings();
		
		for(UserTraining t: trainSet){
			if(t.getTraining().getId() == userTraining.getTraining().getId()
					&& t.getUser().getId() == userTraining.getUser().getId()){
				trainSet.remove(t);
				getCurrentSession().delete(t);
				break;
			}
		}
	}
	
}
