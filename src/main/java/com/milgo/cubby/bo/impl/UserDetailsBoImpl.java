package com.milgo.cubby.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milgo.cubby.bo.UserDetailsBo;
import com.milgo.cubby.dao.UserDetailsDao;
import com.milgo.cubby.model.UserDetails;

@Service
public class UserDetailsBoImpl implements UserDetailsBo{

	@Autowired
	UserDetailsDao userDetailsDao;
	
	public UserDetailsDao getUserDetailsDao() {
		return userDetailsDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}
	
	public void addUser(UserDetails userDetails) {
		userDetailsDao.addUser(userDetails);
	}

	public void removeUser(UserDetails userDetails) {
		userDetailsDao.removeUser(userDetails);
	}

}