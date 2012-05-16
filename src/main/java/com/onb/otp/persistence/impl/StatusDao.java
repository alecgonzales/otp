package com.onb.otp.persistence.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onb.otp.domain.Status;
import com.onb.otp.persistence.base.BaseDao;
import com.onb.otp.persistence.base.StatusDaoBase;

@Repository
@Transactional
public class StatusDao extends BaseDao implements StatusDaoBase {

	@Autowired
	public StatusDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void save(Status status) {
		currentSession().save(status);
	}
	
	@Override
	public Status getById(long id) {
		return (Status) currentSession().get(Status.class, id);
	}
	
	@Override
	public void update(Status status) {
		currentSession().merge(status);
	}
	
	@Override
	public Status getByValue(String value) {
		Status status = null;
        String sql = "FROM Status s WHERE s.value = :value";
        Query query = currentSession().createQuery(sql).setParameter("value", value);
        status = (Status) query.uniqueResult();
        return status;
	}
}
