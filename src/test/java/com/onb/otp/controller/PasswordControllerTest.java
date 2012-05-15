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
	public void generateOtpWithExpiryDate() throws HttpRequestMethodNotSupportedException, ParseException {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		
	    when(passwordService.generatePasswordList(expiryDate)).thenReturn(new OneTimePasswordList());

		controller.generateOtpWithExpiryDate(expires);
	}
	
	@Test(expected = HttpRequestMethodNotSupportedException.class)
	public void generateOtpInvalidExpiryDate() throws HttpRequestMethodNotSupportedException {
		String expires = "";

		controller.generateOtpWithExpiryDate(expires);
	}
	
	@Test
	public void generateOtpWithMaxAge() throws HttpRequestMethodNotSupportedException, ParseException {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		
	    when(passwordService.generatePasswordList(expiryDate)).thenReturn(new OneTimePasswordList());

		controller.generateOtpWithMaxAge(expires);
	}
	
	@Test
	public void generateBatchOtpWithExpiryDate() throws HttpRequestMethodNotSupportedException, ParseException {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		String size = "20";
		Integer batchSize = Integer.parseInt(size);
		
	    when(passwordService.generateBatchPasswordList(expiryDate, batchSize)).thenReturn(new ArrayList<OneTimePasswordList>());

		controller.generateBatchOtpWithExpiryDate(expires, size);
	}
	
	@Test(expected = HttpRequestMethodNotSupportedException.class)
	public void generateBatchOtpInvalidExpiryDate() throws HttpRequestMethodNotSupportedException {
		String expires = "";
		String size = "20";

		controller.generateBatchOtpWithExpiryDate(expires, size);
	}
	
	@Test(expected = HttpRequestMethodNotSupportedException.class)
	public void generateBatchOtpInvalidBatchSize() throws HttpRequestMethodNotSupportedException {
		String expires = "20120101";
		String size = "AEO";

		controller.generateBatchOtpWithExpiryDate(expires, size);
	}
	
	@Test
	public void generateBatchOtpWithMaxAge() throws HttpRequestMethodNotSupportedException, ParseException {
		String expires = "20120101";
		Date expiryDate = sdf.parse(expires);
		String size = "20";
		Integer batchSize = Integer.parseInt(size);
		
	    when(passwordService.generateBatchPasswordList(expiryDate, batchSize)).thenReturn(new ArrayList<OneTimePasswordList>());

		controller.generateBatchOtpWithMaxAge(expires, size);
	}
}
