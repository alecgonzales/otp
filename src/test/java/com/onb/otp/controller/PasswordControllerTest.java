package com.onb.otp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.onb.otp.domain.OneTimePasswordList;
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
	public void generateOtp() throws HttpRequestMethodNotSupportedException, ParseException {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		
	    when(passwordService.generatePasswordList(expiryDate)).thenReturn(new OneTimePasswordList());

		controller.generateOtp(expires);
	}
	
	@Test(expected = HttpRequestMethodNotSupportedException.class)
	public void generateOtpInvalidExpiryDate() throws HttpRequestMethodNotSupportedException {
		String expires = "";

		controller.generateOtp(expires);
	}
	
	@Test
	public void generateBatchOtp() throws HttpRequestMethodNotSupportedException, ParseException {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		String size = "20";
		Integer batchSize = Integer.parseInt(size);
		
	    when(passwordService.generateBatchPasswordList(expiryDate, batchSize)).thenReturn(new ArrayList<OneTimePasswordList>());

		controller.generateBatchOtp(expires, size);
	}
	
	@Test(expected = HttpRequestMethodNotSupportedException.class)
	public void generateBatchOtpInvalidExpiryDate() throws HttpRequestMethodNotSupportedException {
		String expires = "";
		String size = "20";

		controller.generateBatchOtp(expires, size);
	}
	
	@Test(expected = HttpRequestMethodNotSupportedException.class)
	public void generateBatchOtpInvalidBatchSize() throws HttpRequestMethodNotSupportedException {
		String expires = "20120101";
		String size = "AEO";

		controller.generateBatchOtp(expires, size);
	}
}
