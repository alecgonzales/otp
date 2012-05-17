package com.onb.otp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.onb.otp.domain.User;
import com.onb.otp.persistence.impl.UserDao;

import static org.mockito.Mockito.when;

public class UserServiceTest {
	private UserService userService;
	private UserDao userDao;
	
	@Before
	public void setup() {
		userService = new UserService();

		userDao = mock(UserDao.class);
		userService.userDao = userDao;
	}
	
	@Test
	public void lookupUserByUsername() {
		User expectedUser = new User();
		String username = "john";
		when(userDao.getByUsername(username)).thenReturn(expectedUser);
		
		User user = userService.lookupUserByUsername(username);
		
		assertEquals(expectedUser, user);
	}
}
