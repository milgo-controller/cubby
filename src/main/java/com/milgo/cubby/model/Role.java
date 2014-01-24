package com.milgo.cubby.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ROLES"/*, uniqueConstraints = {
@UniqueConstraint(columnNames = "ROLE") //how to set message?
}*/)
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private int id;
	
	@Column(name="ROLE", unique=true, nullable=false)
	private String role;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLES", 
		joinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")},
		inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")})
	private List<User> userList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userRoles) {
		this.userList = userRoles;
	}
	
}
