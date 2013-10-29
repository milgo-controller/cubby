package com.milgo.cubby.bo;

import com.milgo.cubby.model.UserDetails;

public interface UserDetailsBo {

	public void addUser(UserDetails userDetails);
	public void removeUser(UserDetails userDetails);
	public UserDetails getUser(String login);
	
}