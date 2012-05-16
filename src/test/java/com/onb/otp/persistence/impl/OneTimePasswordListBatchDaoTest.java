package com.onb.otp.persistence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.persistence.base.OneTimePasswordListBatchDaoBase;
import com.onb.otp.persistence.base.OneTimePasswordListDaoBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class OneTimePasswordListBatchDaoTest extends DaoTestBase {
	@Autowired
	private OneTimePasswordListBatchDaoBase dao;
	@Autowired
	private OneTimePasswordListDaoBase otpListDao;
	
	@Test
	public void save() {
		OneTimePasswordListBatch passwordListBatch = new OneTimePasswordListBatch();
		dao.save(passwordListBatch);
	}
	
	@Test
	public void getById() {
        OneTimePasswordListBatch passwordListBatch = dao.getById(1L);
        assertNotNull(passwordListBatch);
    }
	
	@Test
	@Transactional
	public void update() {
		OneTimePasswordList oneTimePasswordList1 = otpListDao.getById(4L);
		OneTimePasswordList oneTimePasswordList2 = otpListDao.getById(5L);
		Set<OneTimePasswordList> passwordLists = new LinkedHashSet<OneTimePasswordList>();
		passwordLists.add(oneTimePasswordList1);
		passwordLists.add(oneTimePasswordList2);
		
		OneTimePasswordListBatch passwordListBatch = dao.getById(1L);
		passwordListBatch.setPasswordLists(passwordLists);
		dao.update(passwordListBatch);
		
		OneTimePasswordListBatch newPasswordListBatch = dao.getById(1L);
		assertEquals(passwordLists, newPasswordListBatch.getPasswordLists());
	}
}
