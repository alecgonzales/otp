package com.onb.otp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.exception.InvalidExpiryDateException;
import com.onb.otp.service.impl.PasswordService;

@Controller
public class PasswordController {
	
	@Autowired
	PasswordService passwordService;
	
	@RequestMapping(value="/otp-list", method=RequestMethod.POST) 
	public @ResponseBody OneTimePasswordList generateOtp(@RequestParam("expires") String expires) throws HttpRequestMethodNotSupportedException {
		try {
			Date expiryDate = parseExpiryDate(expires); 
			return passwordService.generatePasswordList(expiryDate);
		} catch (InvalidExpiryDateException e) {
			throw new HttpRequestMethodNotSupportedException("Invalid expiryDate: " + expires + ". Must be in yyyymmdd format.");
		}
	}
	
	private Date parseExpiryDate(String expiry) throws InvalidExpiryDateException {
		Date expiryDate;
		DateFormat sdf = new SimpleDateFormat("yyyymmdd");
		try {
			expiryDate = sdf.parse(expiry);
		} catch (ParseException e) {
			throw new InvalidExpiryDateException("Invalid date - " + expiry);
		}
		return expiryDate;
	}
	
	@RequestMapping(value="/otp-list/_batch", method=RequestMethod.POST) 
	public @ResponseBody List<OneTimePasswordList> generateBatchOtp(@RequestParam("expires") String expires, @RequestParam("batch-size") String batchSize) throws HttpRequestMethodNotSupportedException {
		try {
			Date expiryDate = parseExpiryDate(expires);
			Integer size = Integer.parseInt(batchSize);
			return passwordService.generateBatchPasswordList(expiryDate, size);
		} catch (InvalidExpiryDateException e) {
			throw new HttpRequestMethodNotSupportedException("Invalid expires parameter: " + expires + ". Must be in yyyymmdd format.");
		} catch (NumberFormatException e) {
			throw new HttpRequestMethodNotSupportedException("Invalid batch-size parameter: " + batchSize + ". Must be a valid integer.");
		}
	}
}
