package com.onb.otp.persistence.impl;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.persistence.base.OneTimePasswordDaoBase;

@Repository
public class OneTimePasswordDao implements OneTimePasswordDaoBase {
	private SessionFactory sessionFactory;
	
	@Autowired
	public OneTimePasswordDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(OneTimePassword password) {
		currentSession().save(password);
	}

	@Override
	public OneTimePassword getById(long id) {
		return (OneTimePassword) currentSession().get(OneTimePassword.class, id);
	}

	@Override
	public void update(OneTimePassword password) {
		currentSession().update(password);
	}
}
