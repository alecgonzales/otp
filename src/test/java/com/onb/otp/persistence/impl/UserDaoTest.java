package com.onb.otp.persistence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.otp.domain.User;
import com.onb.otp.persistence.base.UserDaoBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class UserDaoTest extends DaoTestBase {
	@Autowired
	private UserDaoBase dao;
	@Autowired
	
	@Test
	public void save() {
		User user = new User();
		user.setUsername("user");
		dao.save(user);
	}
	
	@Test
	public void getById() {
        User user = dao.getById(1L);
        assertNotNull(user);
    }
	
	@Test
	public void update() {
		User user = dao.getById(1L);
		user.setUsername("newuser");
		dao.update(user);
		
		User newUser = dao.getById(1L);
		assertEquals("newuser", newUser.getUsername());
	}
}
