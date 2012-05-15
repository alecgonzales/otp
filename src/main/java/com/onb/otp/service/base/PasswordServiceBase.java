package com.onb.otp.service.base;

import java.util.Date;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;

public interface PasswordServiceBase {
	public OneTimePasswordList generatePasswordList(Date expires);
	public OneTimePasswordListBatch generateBatchPasswordList(Date expiryDate, Integer batchSize);
}
