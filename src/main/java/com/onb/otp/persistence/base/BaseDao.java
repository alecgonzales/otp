package com.onb.otp.persistence.base;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {
	private SessionFactory sessionFactory;
	
	@Autowired
	public BaseDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
}
