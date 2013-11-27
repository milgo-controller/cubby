package com.milgo.cubby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.milgo.cubby.validator.annotations.FieldMatch;
import com.milgo.cubby.validator.annotations.UniqueField;

@Entity
@Table(name="user_details")
@FieldMatch.List(
		value = { 
				@FieldMatch(first="password", 
						second="confirmPassword", 
						message="The password fields must match")
		} )
@UniqueField(fieldName="login", message="Login used!")
public class UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	public Integer Id;
	
	//no whitespaces
	@Size(min = 5, max = 15, message = "Login to short")
	//@UniqueLogin(message="Login is used!")
	public String login;
	
	@Size(min = 5, max = 15, message = "Password to short!")
	public String password;
	
	@Transient
	public String confirmPassword;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
	//no whitespaces
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
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
