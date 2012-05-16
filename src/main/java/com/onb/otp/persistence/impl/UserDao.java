package com.onb.otp.persistence.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onb.otp.domain.User;
import com.onb.otp.persistence.base.BaseDao;
import com.onb.otp.persistence.base.UserDaoBase;

@Repository
@Transactional
public class UserDao extends BaseDao implements UserDaoBase {

	@Autowired
	public UserDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void save(User user) {
		currentSession().save(user);
	}
	
	@Override
	public User getById(long id) {
		return (User) currentSession().get(User.class, id);
	}
	
	@Override
	public void update(User user) {
		currentSession().merge(user);
	}
}
