package com.onb.otp.service.impl;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.domain.User;
import com.onb.otp.exception.OneTimePasswordListNotFreeException;
import com.onb.otp.persistence.base.OneTimePasswordListBatchDaoBase;
import com.onb.otp.persistence.base.OneTimePasswordListDaoBase;
import com.onb.otp.persistence.base.StatusDaoBase;
import com.onb.otp.persistence.base.UserDaoBase;
import com.onb.otp.service.base.PasswordServiceBase;

@Service
@Transactional
public class PasswordService implements PasswordServiceBase {
	
	private static final int LIST_SIZE = 50;
	@Autowired
	OneTimePasswordListDaoBase passwordListDao;
	@Autowired
	OneTimePasswordListBatchDaoBase passwordListBatchDao;
	@Autowired
	UserDaoBase userDao;
	@Autowired
	StatusDaoBase statusDao;

	/**
	 * Generates a list of one time passwords.
	 * @return list of one time passwords
	 */
	@Override
	public OneTimePasswordList generatePasswordList(Date expiryDate) {
		OneTimePasswordList passwordList = new OneTimePasswordList();
		passwordList.setPasswords(generatePasswords());
		passwordList.setStatus(statusDao.getByValue("free"));
		passwordList.setSize(LIST_SIZE);
		passwordList.setExpires(expiryDate);
		passwordListDao.save(passwordList);
		return passwordList;
	}
	
	private Set<OneTimePassword> generatePasswords() {
		Set<OneTimePassword> passwords = new LinkedHashSet<OneTimePassword>();
		for(int index=1; index<=LIST_SIZE; index++) {
			passwords.add(new OneTimePassword(index, RandomStringUtils.randomAlphanumeric(10)));
		}
		return passwords;
	}
	
	/**
	 * Generates a batch of password lists.
	 * @return list of password lists
	 */
	@Override
	public OneTimePasswordListBatch generateBatchPasswordList(Date expiryDate, Integer batchSize) {
		OneTimePasswordListBatch batch = new OneTimePasswordListBatch();
		Set<OneTimePasswordList> passwordListBatch = new LinkedHashSet<OneTimePasswordList>();
		for(int index=0; index<batchSize; index++) {
			passwordListBatch.add(generatePasswordList(expiryDate));
		}
		batch.setPasswordLists(passwordListBatch);
		batch.setBatchSize(batchSize);
		passwordListBatchDao.save(batch);
		return batch;
	}
	
	/**
	 * Associates an otp list with a user.
	 * @return associated otp list
	 */
	@Override
	public OneTimePasswordList associateOtpListWithUser(OneTimePasswordList passwordList, User user) throws OneTimePasswordListNotFreeException {
		validateListIsFree(passwordList);
		passwordList.setUser(user);
		passwordList.setStatus(statusDao.getByValue("associated"));
		passwordListDao.update(passwordList);
		return passwordList;
	}
	
	private void validateListIsFree(OneTimePasswordList passwordList) throws OneTimePasswordListNotFreeException {
		if (!passwordList.isFree()) {
			throw new OneTimePasswordListNotFreeException("Otp list with id " + passwordList.getId() + " is no longer free.");
		}
	}

	@Override
	public void deleteOtpList(OneTimePasswordList passwordList) {
		passwordListDao.delete(passwordList);
	}
}
