package com.onb.otp.transformer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.onb.otp.datatransferobject.OtpListForLookupUser;
import com.onb.otp.datatransferobject.StatusForLookupUser;
import com.onb.otp.datatransferobject.UserForLookupUser;
import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.Status;
import com.onb.otp.domain.User;

public class UserTransformerTest {
	private UserTransformer transformer = new UserTransformer();
	
	@Test
	public void transformUserForLookupUser() {
		Status status = new Status();
		status.setValue("free");
		status.setReferenceIndex("B1");
		
		OneTimePasswordList list = new OneTimePasswordList();
		list.setId(1L);
		list.setStatus(status);
		
		Set<OneTimePasswordList> lists = new LinkedHashSet<OneTimePasswordList>();
		lists.add(list);
		
		User user = new User();
		user.setUsername("user");
		user.setPasswordLists(lists);
		
		UserForLookupUser userObject = transformer.transformUserForLookupUser(user);
		
		List<OtpListForLookupUser> otpObjects = new ArrayList<OtpListForLookupUser>(userObject.getOtps());
		OtpListForLookupUser otpObject = otpObjects.get(0);
		Long expectedId = Long.parseLong("1");
		StatusForLookupUser statusObject = otpObject.getStatus();
		
		assertEquals("user", userObject.getUniqueID());
		assertEquals(expectedId, otpObject.getId());
		assertEquals("B1", statusObject.getIndex());
		assertEquals("free", statusObject.getValue());
	}
}
