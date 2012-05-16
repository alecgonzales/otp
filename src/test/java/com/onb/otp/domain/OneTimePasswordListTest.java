package com.onb.otp.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OneTimePasswordListTest {
	@Test
	public void isFree() {
		OneTimePasswordList passwordList = new OneTimePasswordList();
		Status freeStatus = new Status();
		freeStatus.setValue("free");
		passwordList.setStatus(freeStatus);
		
		assertTrue(passwordList.isFree());
	}
}

