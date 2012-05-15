package com.onb.otp.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.service.base.PasswordServiceBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class PasswordServiceTest {
	@Autowired
	PasswordServiceBase passwordService;

	@Test
	public void generatePasswordList() {
		OneTimePasswordList passwordList = passwordService.generatePasswordList(new Date());
		
		assertNotNull(passwordList);
		assertNotNull(passwordList.getPasswords());
	}
	
	@Test
	public void generateBatchPasswordList() {
		OneTimePasswordListBatch batchPasswordList = passwordService.generateBatchPasswordList(new Date(), 10);
		
		assertNotNull(batchPasswordList);
	}
}
