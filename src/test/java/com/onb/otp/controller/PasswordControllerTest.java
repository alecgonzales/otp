package com.onb.otp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.exception.InvalidRequestParameterException;
import com.onb.otp.service.impl.PasswordService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PasswordControllerTest {
	private PasswordController controller;
	private PasswordService passwordService;
	private DateFormat sdf = new SimpleDateFormat("yyyymmdd");
	
	@Before
	public void setup() {
		controller = new PasswordController();
		
		passwordService = mock(PasswordService.class);
		
		controller.passwordService = passwordService;
	}
	
	@Test
	public void generateOtpWithExpiryDate() throws Exception {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		
	    when(passwordService.generatePasswordList(expiryDate)).thenReturn(new OneTimePasswordList());

		controller.generateOtpWithExpiryDate(expires);
	}
	
	@Test(expected = InvalidRequestParameterException.class)
	public void generateOtpInvalidExpiryDate() throws Exception {
		String expires = "";

		controller.generateOtpWithExpiryDate(expires);
	}
	
	@Test
	public void generateOtpWithMaxAge() throws Exception, ParseException {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		
	    when(passwordService.generatePasswordList(expiryDate)).thenReturn(new OneTimePasswordList());

		controller.generateOtpWithMaxAge(expires);
	}
	
	@Test
	public void generateBatchOtpWithExpiryDate() throws Exception {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		String size = "20";
		Integer batchSize = Integer.parseInt(size);
		
	    when(passwordService.generateBatchPasswordList(expiryDate, batchSize)).thenReturn(new OneTimePasswordListBatch());

		controller.generateBatchOtpWithExpiryDate(expires, size);
	}
	
	@Test(expected = InvalidRequestParameterException.class)
	public void generateBatchOtpInvalidExpiryDate() throws Exception {
		String expires = "";
		String size = "20";

		controller.generateBatchOtpWithExpiryDate(expires, size);
	}
	
	@Test(expected = InvalidRequestParameterException.class)
	public void generateBatchOtpInvalidBatchSize() throws Exception {
		String expires = "20120101";
		String size = "AEO";

		controller.generateBatchOtpWithExpiryDate(expires, size);
	}
	
	@Test
	public void generateBatchOtpWithMaxAge() throws Exception {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		String size = "20";
		Integer batchSize = Integer.parseInt(size);
		
	    when(passwordService.generateBatchPasswordList(expiryDate, batchSize)).thenReturn(new OneTimePasswordListBatch());

		controller.generateBatchOtpWithMaxAge(expires, size);
	}
}
