package com.onb.otp.persistence.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.persistence.base.BaseDao;
import com.onb.otp.persistence.base.OneTimePasswordListBatchDaoBase;

@Repository
@Transactional
public class OneTimePasswordListBatchDao extends BaseDao implements OneTimePasswordListBatchDaoBase {
	@Autowired
	public OneTimePasswordListBatchDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void save(OneTimePasswordListBatch passwordListBatch) {
		currentSession().save(passwordListBatch);
	}

	@Override
	public OneTimePasswordListBatch getById(long id) {
		return (OneTimePasswordListBatch) currentSession().get(OneTimePasswordListBatch.class, id);
	}

	@Override
	public void update(OneTimePasswordListBatch passwordListBatch) {
		currentSession().update(passwordListBatch);
	}
}
