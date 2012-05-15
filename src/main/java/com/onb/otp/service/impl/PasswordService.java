package com.onb.otp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.persistence.base.OneTimePasswordListDaoBase;
import com.onb.otp.service.base.PasswordServiceBase;

@Service
public class PasswordService implements PasswordServiceBase {
	
	private static final int LIST_SIZE = 50;
	@Autowired
	private OneTimePasswordListDaoBase passwordListDao;

	/**
	 * Generates a list of one time passwords.
	 * @return list of one time passwords
	 */
	public OneTimePasswordList generatePasswordList(Date expiryDate) {
		OneTimePasswordList passwordList = new OneTimePasswordList();
		passwordList.setPasswords(generatePasswords());
		passwordList.setSize(LIST_SIZE);
		passwordList.setExpires(expiryDate);
		passwordListDao.save(passwordList);
		return passwordList;
	}
	
	private Set<OneTimePassword> generatePasswords() {
		Set<OneTimePassword> passwords = new HashSet<OneTimePassword>();
		for(int index=0; index<LIST_SIZE; index++) {
			passwords.add(new OneTimePassword(RandomStringUtils.randomAlphanumeric(10)));
		}
		return passwords;
	}
	
	/**
	 * Generates a batch of password lists.
	 * @return list of password lists
	 */
	public List<OneTimePasswordList> generateBatchPasswordList(Date expiryDate, Integer batchSize) {
		List<OneTimePasswordList> batchPasswordList = new ArrayList<OneTimePasswordList>();
		for(int index=0; index<batchSize; index++) {
			batchPasswordList.add(generatePasswordList(expiryDate));
		}
		return batchPasswordList;
	}
}
