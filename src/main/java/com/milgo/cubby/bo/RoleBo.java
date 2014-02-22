package com.milgo.cubby.bo;

import java.util.List;

import com.milgo.cubby.model.Role;

public interface RoleBo {

	public void addRole(Role role);
	public void removeRole(Role role);
	public Role getRoleByName(String name);
	public List<?> getAllRoles();
	
}