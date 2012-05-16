package com.onb.otp.persistence.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.persistence.base.BaseDao;
import com.onb.otp.persistence.base.OneTimePasswordDaoBase;

@Repository
@Transactional
public class OneTimePasswordDao extends BaseDao implements OneTimePasswordDaoBase {
	@Autowired
	public OneTimePasswordDao(SessionFactory sessionFactory) {
		super(sessionFactory);
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
		currentSession().merge(password);
	}
}
