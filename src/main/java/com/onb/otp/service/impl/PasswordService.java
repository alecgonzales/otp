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
import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.persistence.base.OneTimePasswordListBatchDaoBase;
import com.onb.otp.persistence.base.OneTimePasswordListDaoBase;
import com.onb.otp.service.base.PasswordServiceBase;

@Service
public class PasswordService implements PasswordServiceBase {
	
	private static final int LIST_SIZE = 50;
	@Autowired
	private OneTimePasswordListDaoBase passwordListDao;
	@Autowired
	private OneTimePasswordListBatchDaoBase passwordListBatchDao;

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
		for(int index=1; index<=LIST_SIZE; index++) {
			passwords.add(new OneTimePassword(index, RandomStringUtils.randomAlphanumeric(10)));
		}
		return passwords;
	}
	
	/**
	 * Generates a batch of password lists.
	 * @return list of password lists
	 */
	public OneTimePasswordListBatch generateBatchPasswordList(Date expiryDate, Integer batchSize) {
		OneTimePasswordListBatch batch = new OneTimePasswordListBatch();
		List<OneTimePasswordList> passwordListBatch = new ArrayList<OneTimePasswordList>();
		for(int index=0; index<batchSize; index++) {
			passwordListBatch.add(generatePasswordList(expiryDate));
		}
		batch.setPasswordLists(passwordListBatch);
		batch.setBatchSize(batchSize);
		passwordListBatchDao.save(batch);
		return batch;
	}
}
