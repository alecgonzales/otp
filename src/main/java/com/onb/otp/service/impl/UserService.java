package com.onb.otp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onb.otp.domain.User;
import com.onb.otp.exception.InvalidRequestParameterException;
import com.onb.otp.persistence.base.UserDaoBase;
import com.onb.otp.service.base.UserServiceBase;

@Service
public class UserService implements UserServiceBase {
	@Autowired
	UserDaoBase userDao;
	
	/**
	 * Retrieve user with given username.
	 * @return user with given username
	 */
	@Override
	public User lookupUserByUsername(String username) throws InvalidRequestParameterException {
		User user = userDao.getByUsername(username);
		if (null == user) {
			throw new InvalidRequestParameterException("Invalid username: " + username + ".");
		}
		return user;
	}
	
}
