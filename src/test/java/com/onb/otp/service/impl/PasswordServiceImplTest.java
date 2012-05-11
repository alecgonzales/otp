package com.onb.otp.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.service.base.PasswordService;

public class PasswordServiceImplTest {

	@Test
	public void generatePasswordList() {
		PasswordService passwordService = new PasswordServiceImpl();
		OneTimePasswordList passwordList = passwordService.generatePasswordList();
		
		assertNotNull(passwordList);
	}
}
