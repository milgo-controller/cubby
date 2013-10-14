package com.milgo.cubby.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_details")
public class UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	public Integer Id;
	
	@Size(min = 5, max = 15, message = "Login to short")
	public String login;
	
	@Size(min = 5, max = 15, message = "Password to short!")
	public String password;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
	@Column(name="LOGIN", unique=true, nullable=false)
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name="PASSWORD", nullable=false)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
