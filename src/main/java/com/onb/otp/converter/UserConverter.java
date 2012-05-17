package com.onb.otp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.onb.otp.domain.User;
import com.onb.otp.exception.InvalidRequestParameterException;
import com.onb.otp.persistence.base.UserDaoBase;

public class UserConverter implements Converter<String, User> {
	@Autowired
	UserDaoBase dao;
	
	@Override
	public User convert(String id) throws InvalidRequestParameterException {
		return find(id);
	}
	
	private User find(String id) throws InvalidRequestParameterException {
		User user = null;
		try {
			user = dao.getById(Long.parseLong(id));
		} catch (NumberFormatException e) {
			user = null;
		}
		return user;
	}
}
