package com.milgo.cubby.model;

import java.util.List;

import javax.persistence.Cacheable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name="ROLES")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	
	@Column(name="ROLE", unique=true, nullable=false)
	private String roleName;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
	@JoinTable(name="USER_ROLES", 
		joinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")}/*,
		inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")}*/)
	private List<User> userList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userRoles) {
		this.userList = userRoles;
	}
	
}
