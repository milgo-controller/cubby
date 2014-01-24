package com.milgo.cubby.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milgo.cubby.bo.RoleBo;
import com.milgo.cubby.dao.RoleDao;
import com.milgo.cubby.model.Role;

@Service
public class RoleBoImpl implements RoleBo{

	@Autowired
	RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setUserDetailsDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		
	}

	public void removeRole(Role role) {
		roleDao.removeRole(role);
	}

	public Role getUserByName(String name) {
		return roleDao.getRoleByName(name);
	}

	@Override
	public Role getRoleIdByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}