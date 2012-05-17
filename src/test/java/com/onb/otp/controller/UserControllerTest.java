package com.onb.otp.controller;

import static org.mockito.Mockito.mock;

import java.util.LinkedHashSet;

import org.junit.Before;
import org.junit.Test;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.User;
import com.onb.otp.service.impl.UserService;
import com.onb.otp.transformer.UserTransformer;

import static org.mockito.Mockito.when;

public class UserControllerTest {
	private UserController controller;
	private UserService userService;
	private UserTransformer userTrasformer = new UserTransformer();
	
	@Before
	public void setup() {
		controller = new UserController();
		
		userService = mock(UserService.class);
		
		controller.userService = userService;
		controller.userTransformer = userTrasformer;
	}
	
	@Test
	public void lookupUserWithUsername() {
		User user = new User();
		user.setPasswordLists(new LinkedHashSet<OneTimePasswordList>());
		
		when(userService.lookupUserByUsername("user")).thenReturn(user);
		
		controller.lookupUserWithUsername("user");
	}
}
