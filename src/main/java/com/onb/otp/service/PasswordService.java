package com.onb.otp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

public class PasswordService {
	
	public List<String> generatePasswords() {
		List<String> passwords = new ArrayList<String>();
		for(int index=0; index<100; index++) {
			passwords.add(RandomStringUtils.randomAlphanumeric(10));
		}
		return passwords;
	}
	
}
