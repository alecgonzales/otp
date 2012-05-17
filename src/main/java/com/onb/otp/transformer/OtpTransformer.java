package com.onb.otp.transformer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.onb.otp.datatransferobject.OtpForCreate;
import com.onb.otp.datatransferobject.OtpListBatchForCreateBatch;
import com.onb.otp.datatransferobject.OtpListForCreate;
import com.onb.otp.datatransferobject.OtpListForCreateBatch;
import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;

@Service
public class OtpTransformer {
	
	public OtpListForCreate transformOtpListForCreate(OneTimePasswordList passwordList) {
		List<OtpForCreate> otps = new ArrayList<OtpForCreate>();
		for (OneTimePassword password : passwordList.getPasswords()) {
			OtpForCreate otp = new OtpForCreate();
			otp.setIndex(password.getReferenceIndex());
			otp.setValue(password.getCode());
			otps.add(otp);
		}
		OtpListForCreate otpList = new OtpListForCreate();
		otpList.setId(passwordList.getId());
		otpList.setSize(passwordList.getSize());
		otpList.setExpires(passwordList.getExpires());
		otpList.setOtps(otps);
		return otpList;
	}
	
	public OtpListBatchForCreateBatch transformOtpListBatchForCreate(OneTimePasswordListBatch batch) {
		Set<OtpListForCreateBatch> otpsBatch = new LinkedHashSet<OtpListForCreateBatch>();
		for(OneTimePasswordList passwordList : batch.getPasswordLists()) {
			OtpListForCreateBatch otpBatch = new OtpListForCreateBatch();
			otpBatch.setId(passwordList.getId());
			otpsBatch.add(otpBatch);
		}
		OtpListBatchForCreateBatch otpBatchList= new OtpListBatchForCreateBatch();
		otpBatchList.setSize(batch.getBatchSize());
		otpBatchList.setLists(otpsBatch);
		return otpBatchList;
	}
	
}
