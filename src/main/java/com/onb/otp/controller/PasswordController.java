package com.onb.otp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.onb.otp.domain.ErrorMessage;
import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.exception.InvalidExpiryDateException;
import com.onb.otp.exception.InvalidRequestParameterException;
import com.onb.otp.service.impl.PasswordService;

@Controller
public class PasswordController {
	
	@Autowired
	PasswordService passwordService;
	
	@RequestMapping(value="/otp-list", method=RequestMethod.POST, params="expires") 
	public @ResponseBody OneTimePasswordList generateOtpWithExpiryDate(@RequestParam("expires") String expires) throws InvalidRequestParameterException {
		return generateOtp(expires);
	}
	
	@RequestMapping(value="/otp-list", method=RequestMethod.POST, params="max-age") 
	public @ResponseBody OneTimePasswordList generateOtpWithMaxAge(@RequestParam("max-age") String expires) throws InvalidRequestParameterException {
		return generateOtp(expires);
	}
	
	private OneTimePasswordList generateOtp(String expires) throws InvalidRequestParameterException {
		try {
			Date expiryDate = parseExpiryDate(expires); 
			return passwordService.generatePasswordList(expiryDate);
		} catch (InvalidExpiryDateException e) {
			throw new InvalidRequestParameterException("Invalid expiryDate: " + expires + ". Must be in yyyymmdd format.");
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
	
	@RequestMapping(value="/otp-list/_batch", method=RequestMethod.POST,  params={"expires", "batch-size"}) 
	public @ResponseBody OneTimePasswordListBatch generateBatchOtpWithExpiryDate(@RequestParam("expires") String expires, @RequestParam("batch-size") String batchSize) throws InvalidRequestParameterException {
		return generateBatchOtp(expires, batchSize);
	}
	
	@RequestMapping(value="/otp-list/_batch", method=RequestMethod.POST,  params={"max-age", "batch-size"}) 
	public @ResponseBody OneTimePasswordListBatch generateBatchOtpWithMaxAge(@RequestParam("max-age") String expires, @RequestParam("batch-size") String batchSize) throws InvalidRequestParameterException {
		return generateBatchOtp(expires, batchSize);
	}
	
	private OneTimePasswordListBatch generateBatchOtp(String expires, String batchSize) throws InvalidRequestParameterException {
		try {
			Date expiryDate = parseExpiryDate(expires);
			Integer size = Integer.parseInt(batchSize);
			return passwordService.generateBatchPasswordList(expiryDate, size);
		} catch (InvalidExpiryDateException e) {
			throw new InvalidRequestParameterException("Invalid expires parameter: " + expires + ". Must be in yyyymmdd format.");
		} catch (NumberFormatException e) {
			throw new InvalidRequestParameterException("Invalid batch-size parameter: " + batchSize + ". Must be a valid integer.");
		}
	}
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorMessage handleException(Throwable ex, HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return new ErrorMessage("404", ex.getMessage());
	}
	
}