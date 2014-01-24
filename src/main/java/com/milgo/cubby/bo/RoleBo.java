package com.milgo.cubby.bo;

import com.milgo.cubby.model.Role;

public interface RoleBo {

	public void addRole(Role role);
	public void removeRole(Role role);
	public Role getRoleIdByName(String name);
	
}

//http://fruzenshtein.com/spring-mvc-security-mysql-hibernate/
//http://fruzenshtein.com/hibernate-join-table-intermediary/