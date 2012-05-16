package com.onb.otp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.exception.InvalidRequestParameterException;
import com.onb.otp.persistence.base.OneTimePasswordListDaoBase;

public class OneTimePasswordListConverter implements Converter<String, OneTimePasswordList> {
	@Autowired
	OneTimePasswordListDaoBase dao;
	
	@Override
	public OneTimePasswordList convert(String id) throws InvalidRequestParameterException {
		OneTimePasswordList passwordList = find(id);
		if (null == passwordList) {
			throwException(id);
		}
		return passwordList;
	}
	
	private OneTimePasswordList find(String id) throws InvalidRequestParameterException {
		OneTimePasswordList passwordList = null;
		try {
			passwordList = dao.getById(Long.parseLong(id));
		} catch (NumberFormatException e) {
			throwException(id);
		}
		return passwordList;
	}
	
	private void throwException(String id) throws InvalidRequestParameterException {
		throw new InvalidRequestParameterException("Invalid otp list id: " + id + ".");
	}
	
}
