package com.onb.otp.service.base;

import java.util.Date;
import java.util.List;

import com.onb.otp.domain.OneTimePasswordList;

public interface PasswordServiceBase {
	public OneTimePasswordList generatePasswordList(Date expires);
	public List<OneTimePasswordList> generateBatchPasswordList(Date expiryDate, Integer batchSize);
}
