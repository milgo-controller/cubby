package com.milgo.cubby.dao;

import com.milgo.cubby.model.UserDetails;

public interface UserDetailsDao {

	public void addUser(UserDetails userDetails);
	public void removeUser(UserDetails userDetails);
	public UserDetails getUser(String login);
	
}
