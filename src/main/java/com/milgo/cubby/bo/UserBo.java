package com.milgo.cubby.bo;

import com.milgo.cubby.model.User;

public interface UserBo {

	public void addUser(User user);
	public void removeUser(User user);
	public User getUserByLogin(String login);
	
}