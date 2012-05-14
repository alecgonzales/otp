package com.onb.otp.persistence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.persistence.base.OneTimePasswordDaoBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class OneTimePasswordDaoTest extends DaoTestBase {
	@Autowired
	private OneTimePasswordDaoBase dao;
	
	@Test
	public void save() {
		OneTimePassword password = new OneTimePassword("password");
		dao.save(password);
	}
	
	@Test
	public void getById() {
        OneTimePassword password = dao.getById(1L);
        assertNotNull(password);
    }
	
	@Test
	public void update() {
		OneTimePassword password = dao.getById(1L);
		password.setCode("new password");
		dao.update(password);
		
		OneTimePassword newPassword = dao.getById(1L);
		assertEquals("new password", newPassword.getCode());
	}
}
