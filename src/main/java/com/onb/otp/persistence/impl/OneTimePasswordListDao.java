package com.onb.otp.persistence.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.persistence.base.BaseDao;
import com.onb.otp.persistence.base.OneTimePasswordListDaoBase;

@Repository
public class OneTimePasswordListDao extends BaseDao implements OneTimePasswordListDaoBase {
	@Autowired
	public OneTimePasswordListDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void save(OneTimePasswordList passwordList) {
		currentSession().save(passwordList);
	}

	@Override
	public OneTimePasswordList getById(long id) {
		return (OneTimePasswordList) currentSession().get(OneTimePasswordList.class, id);
	}

	@Override
	public void update(OneTimePasswordList passwordList) {
		currentSession().update(passwordList);
	}
}
