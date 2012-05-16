package com.onb.otp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.domain.Status;
import com.onb.otp.domain.User;
import com.onb.otp.exception.OneTimePasswordListNotFreeException;
import com.onb.otp.persistence.impl.OneTimePasswordListBatchDao;
import com.onb.otp.persistence.impl.OneTimePasswordListDao;
import com.onb.otp.persistence.impl.StatusDao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/otp-servlet.xml"})
public class PasswordServiceTest {
	private PasswordService passwordService;
	private OneTimePasswordListDao passwordListDao;
	private OneTimePasswordListBatchDao passwordListBatchDao;
	private StatusDao statusDao;
	
	@Before
	public void setup() {
		passwordService = new PasswordService();

		passwordListDao = mock(OneTimePasswordListDao.class);
		passwordService.passwordListDao = passwordListDao;
		
		passwordListBatchDao = mock(OneTimePasswordListBatchDao.class);
		passwordService.passwordListBatchDao = passwordListBatchDao;
		
		statusDao = mock(StatusDao.class);
		passwordService.statusDao = statusDao;
	}
	
	@Test
	public void generatePasswordList() {
		Status status = new Status();
		status.setValue("free");
		when(statusDao.getByValue("free")).thenReturn(status);
		
		OneTimePasswordList passwordList = passwordService.generatePasswordList(new Date());
		
		assertNotNull(passwordList);
		assertNotNull(passwordList.getPasswords());
		assertEquals("free", passwordList.getStatus().getValue());
	}
	
	@Test
	public void generateBatchPasswordList() {
		OneTimePasswordListBatch batchPasswordList = passwordService.generateBatchPasswordList(new Date(), 10);
		
		assertNotNull(batchPasswordList);
	}
	
	@Test
	public void associateOtpListWithUser() {
		OneTimePasswordList list = new OneTimePasswordList();
		Status freeStatus = new Status();
		freeStatus.setValue("free");
		list.setStatus(freeStatus);
		User user = new User();
		Status associatedStatus = new Status();
		associatedStatus.setValue("associated");
		
		doNothing().when(passwordListDao).update(list);
		when(statusDao.getByValue("associated")).thenReturn(associatedStatus);
		OneTimePasswordList passwordList = passwordService.associateOtpListWithUser(list, user);
		
		assertEquals(user, passwordList.getUser());
		assertEquals("associated", passwordList.getStatus().getValue());
	}
	
	@Test(expected=OneTimePasswordListNotFreeException.class)
	public void associateOtpListWithUserNotFree() {
		OneTimePasswordList list = new OneTimePasswordList();
		Status associatedStatus = new Status();
		associatedStatus.setValue("associated");
		list.setStatus(associatedStatus);
		User user = new User();
		
		passwordService.associateOtpListWithUser(list, user);
	}
}
