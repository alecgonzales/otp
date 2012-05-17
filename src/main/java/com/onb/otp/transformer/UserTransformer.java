package com.onb.otp.transformer;

import java.util.Set;
import org.springframework.stereotype.Service;

import com.onb.otp.datatransferobject.OtpListForLookupUser;
import com.onb.otp.datatransferobject.StatusForLookupUser;
import com.onb.otp.datatransferobject.UserForLookupUser;
import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.Status;
import com.onb.otp.domain.User;

@Service
public class UserTransformer {
	public UserForLookupUser transformUserForLookupUser(User user) {
		UserForLookupUser userObject = new UserForLookupUser();
		userObject.setUniqueID(user.getUsername());
		Set<OtpListForLookupUser> lists = userObject.getOtps();
		
		for (OneTimePasswordList passwordList : user.getPasswordLists()) {
			Status status = passwordList.getStatus();
			
			StatusForLookupUser statusObject = new StatusForLookupUser();
			statusObject.setValue(status.getValue());
			statusObject.setIndex(status.getReferenceIndex());
			
			OtpListForLookupUser otpObject = new OtpListForLookupUser();
			otpObject.setId(passwordList.getId());
			otpObject.setStatus(statusObject);
			
			lists.add(otpObject); 
		}
		
		return userObject;
	}
}
