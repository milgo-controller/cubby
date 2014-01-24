package com.milgo.cubby.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.milgo.cubby.validator.annotations.FieldMatch;

@Entity
@Table(name="USERS")
@FieldMatch.List(
		value = { 
				@FieldMatch(first="password", 
						second="confirmPassword", 
						message="The password fields must match")
		} )
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	public Integer Id;

	@Size(min = 5, max = 15, message = "Wrong login length (5 to 15 letters)")
	@Column(name="LOGIN", unique=true, nullable=false)
	public String login;
	
	@Size(min = 5, max = 15, message = "Wrong password length (5 to 15 letters)")
	@Column(name="PASSWORD", nullable=false)
	public String password;
	
	@Transient
	public String confirmPassword;
	
	@NotNull(message="Enter your email!")
	@Pattern(regexp=".+@.+\\.[a-z]+", message="It is not an email!")
	@Column(name="EMAIL")
	public String email;
		
	@Column(name="ENABLED")
	public int enabled;

	@NotNull
	@Size(min=1, message="Enter your first name!")
	@Column(name="FIRSTNAME")
	public String firstName;


	@NotNull
	@Size(min=1, message="Enter your last name!")
	@Column(name="LASTNAME")
	public String lastName;
	
	@NotNull(message="Enter your birth date!")
	@Past(message="Only the past is valid")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="BIRTHDATE")
	public Date birthDate;
		
	@Embedded
	@Valid
	public Address address;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLES",
			joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	public Role role;
	
	public User(){
		setAddress(new Address());
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
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
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public int getEnabled() {
		return enabled;
	}
	
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
