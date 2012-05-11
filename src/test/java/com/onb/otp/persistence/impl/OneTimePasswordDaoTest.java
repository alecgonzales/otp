package com.onb.otp.persistence.impl;

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
public class OneTimePasswordDaoTest {
	@Autowired
	private OneTimePasswordDaoBase dao;
	
	@Test
	public void testLoadTitle() throws Exception {
        OneTimePassword password = dao.getById(1L);
        assertNotNull(password);
    }
}
