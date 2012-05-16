package com.onb.otp.persistence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.otp.domain.Status;
import com.onb.otp.persistence.base.StatusDaoBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class StatusDaoTest extends DaoTestBase {
	@Autowired
	private StatusDaoBase dao;
	
	@Test
	public void save() {
		Status status = new Status();
		status.setValue("used");
		dao.save(status);
	}
	
	@Test
	public void getById() {
        Status status = dao.getById(1L);
        assertNotNull(status);
    }
	
	@Test
	public void update() {
		Status status = dao.getById(1L);
		status.setValue("new value");
		dao.update(status);
		
		Status newStatus = dao.getById(1L);
		assertEquals("new value", newStatus.getValue());
	}
	
	@Test
	public void getByValue() {
        Status status = dao.getByValue("free");
        assertNotNull(status);
    }
}
