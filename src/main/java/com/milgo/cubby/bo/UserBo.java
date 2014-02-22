package com.milgo.cubby.bo;

import java.util.List;

import com.milgo.cubby.model.User;

public interface UserBo {

	public void addUser(User user);
	public void modifyUser(User user);
	public void removeUser(User user);
	public User getUserByLogin(String login);
	public List<?> getAllUsers();
	public boolean isLoginUsed(String login);
	
}