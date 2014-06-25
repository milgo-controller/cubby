package com.milgo.cubby.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.milgo.cubby.validator.annotations.FieldMatch;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name="USERS")
@FieldMatch.List(
		value = { 
				@FieldMatch(first="password", 
						second="confirmPassword", 
						message="The password fields must match")
		} )
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	public Integer id;

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
	public Integer enabled;

	@Transient
	public String enabledCheckbox;
	
	public String getEnabledCheckbox() {
		return enabledCheckbox;
	}

	public void setEnabledCheckbox(String enabledCheckbox) {
		this.enabledCheckbox = enabledCheckbox;
	}

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
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinTable(name="USER_ROLES",
			joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	public Role role;
	
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="pk.user", cascade=CascadeType.ALL)
	public Set<UserTraining> userTrainings = new HashSet<UserTraining>(0);
	
	@Transient
	public Collection<? extends GrantedAuthority> authorities;
	
	public Set<UserTraining> getUserTrainings() {
		return userTrainings;
	}
	
	public List<Training> getTrainings(){
		ArrayList<Training> t = new ArrayList<Training>();
		Set<?> ut = getUserTrainings();
		Iterator<?> i = ut.iterator();
		while (i.hasNext()) {
			t.add(((UserTraining)i.next()).getTraining());
		}
		return t;
	}

	public void setUserTrainings(Set<UserTraining> userTrainings) {
		this.userTrainings = userTrainings;
	}

	/*
	 * Used only to work with form
	 */
	@Transient
	public String roleName;
	@Transient
	public HashMap<String, String> roleNames;	
	
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
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Integer enabled) {
		this.enabled = new Integer(enabled);
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public HashMap<String,String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(HashMap<String,String>  roleNames) {
		this.roleNames = roleNames;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled==1;
	}
	
}
