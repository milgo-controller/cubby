package com.milgo.cubby.dao;

import com.milgo.cubby.model.Role;

public interface RoleDao {

	public void addRole(Role role);
	public void removeRole(Role role);
	public Role getRoleByName(String name);
	
}
