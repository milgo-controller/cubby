package com.milgo.cubby.model;

import javax.validation.constraints.Size;


public class UserDetails {

	@Size(min = 5, max = 15, message = "Login to short")
	public String login;
	
	@Size(min = 5, max = 15, message = "Password to short!")
	public String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
