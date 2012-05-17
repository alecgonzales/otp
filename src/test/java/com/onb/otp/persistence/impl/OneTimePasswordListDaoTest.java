package com.onb.otp.persistence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.User;
import com.onb.otp.persistence.base.OneTimePasswordListDaoBase;
import com.onb.otp.persistence.base.UserDaoBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class OneTimePasswordListDaoTest extends DaoTestBase {
	@Autowired
	private OneTimePasswordListDaoBase dao;
	@Autowired
	private UserDaoBase userDao;
	
	@Test
	public void save() {
		OneTimePasswordList passwordList = new OneTimePasswordList();
		dao.save(passwordList);
	}
	
	@Test
	public void getById() {
        OneTimePasswordList passwordList = dao.getById(1L);
        assertNotNull(passwordList);
    }
	
	@Test
	public void update() {
		User user = userDao.getById(1L);
		
		OneTimePasswordList passwordList = dao.getById(1L);
		passwordList.setUser(user);
		dao.update(passwordList);
		
		OneTimePasswordList newPasswordList = dao.getById(1L);
		assertEquals(user.getUsername(), newPasswordList.getUser().getUsername());
	}
	
	@Test
	public void delete() {
		OneTimePasswordList passwordList = dao.getById(1L);
		
		dao.delete(passwordList);
		
		OneTimePasswordList deletedPasswordList = dao.getById(1L);
		assertNull(deletedPasswordList);
	}
}
