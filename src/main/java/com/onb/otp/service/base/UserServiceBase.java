package com.onb.otp.service.base;

import com.onb.otp.domain.User;
import com.onb.otp.exception.InvalidRequestParameterException;

public interface UserServiceBase {
	public User lookupUserByUsername(String username) throws InvalidRequestParameterException;
}
