package com.onb.otp.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.service.base.PasswordServiceBase;

public class PasswordServiceTest {

	@Test
	public void generatePasswordList() {
		PasswordServiceBase passwordService = new PasswordService();
		OneTimePasswordList passwordList = passwordService.generatePasswordList();
		
		assertNotNull(passwordList);
	}
}
