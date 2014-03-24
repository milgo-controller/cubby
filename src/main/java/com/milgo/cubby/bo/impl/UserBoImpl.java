package com.milgo.cubby.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milgo.cubby.bo.UserBo;
import com.milgo.cubby.dao.UserDao;
import com.milgo.cubby.model.UserTraining;
import com.milgo.cubby.model.User;

@Service
public class UserBoImpl implements UserBo{

	@Autowired
	UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDetailsDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void removeUser(User user) {
		userDao.removeUser(user);
	}

	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

	public void modifyUser(User user) {
		user.setConfirmPassword(user.getPassword());
		userDao.modifyUser(user);	
	}

	public List<?> getAllUsers() {
		return userDao.getAllUsers();
	}

	public boolean isLoginUsed(String login) {
		return userDao.isLoginUsed(login);
	}

	@Override
	public void removeUserTraining(User user, UserTraining userTraining) {
		// TODO Auto-generated method stub
		userDao.removeUserTraining(user, userTraining);
	}

}