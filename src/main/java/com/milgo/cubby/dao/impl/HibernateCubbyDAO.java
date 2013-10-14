package com.milgo.cubby.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.milgo.cubby.dao.CubbyDAO;

@Repository
public class HibernateCubbyDAO implements CubbyDAO{

	private SessionFactory sessionFactory;
	
	@Autowired
	public HibernateCubbyDAO(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser() {
		// TODO Auto-generated method stub
		
	}
	
}
