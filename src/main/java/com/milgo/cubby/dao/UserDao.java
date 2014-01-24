package com.milgo.cubby.dao;

import java.util.List;

import com.milgo.cubby.model.User;

public interface UserDao {

	public void addUser(User user);
	public void removeUser(User user);
	public User getUserByLogin(String login);
	public List<?> getAllUsers();
	public boolean isLoginUsed(String login);
	
}
