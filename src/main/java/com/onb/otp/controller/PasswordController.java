package com.onb.otp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.onb.otp.datatransferobject.OtpListBatchForCreateBatch;
import com.onb.otp.datatransferobject.OtpListForAssociateOtpListWithUser;
import com.onb.otp.datatransferobject.OtpListForCreate;
import com.onb.otp.datatransferobject.OtpListForLookupOtp;
import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;
import com.onb.otp.domain.User;
import com.onb.otp.exception.InvalidExpiryDateException;
import com.onb.otp.exception.InvalidRequestParameterException;
import com.onb.otp.exception.OneTimePasswordListNotFreeException;
import com.onb.otp.service.base.PasswordServiceBase;
import com.onb.otp.transformer.OtpTransformer;

@Controller
public class PasswordController extends BaseController {
	@Autowired
	PasswordServiceBase passwordService;
	@Autowired
	OtpTransformer otpTransformer;
	
	@RequestMapping(value="/otp-list", method=RequestMethod.POST, params="expires")
	public @ResponseBody OtpListForCreate generateOtpWithExpiryDate(@RequestParam("expires") String expires) throws InvalidRequestParameterException {
		return generateOtp(expires);
	}
	
	@RequestMapping(value="/otp-list", method=RequestMethod.POST, params="max-age") 
	public @ResponseBody OtpListForCreate generateOtpWithMaxAge(@RequestParam("max-age") String expires) throws InvalidRequestParameterException {
		return generateOtp(expires);
	}
	
	private OtpListForCreate generateOtp(String expires) throws InvalidRequestParameterException {
		try {
			Date expiryDate = parseExpiryDate(expires);
			OneTimePasswordList otps = passwordService.generatePasswordList(expiryDate);
			return otpTransformer.transformOtpListForCreate(otps);
		} catch (InvalidExpiryDateException e) {
			throw new InvalidRequestParameterException("Invalid expiryDate: " + expires + ". Must be in yyyymmdd format.");
		}
	}
	
	private Date parseExpiryDate(String expiry) throws InvalidExpiryDateException {
		Date expiryDate;
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			expiryDate = sdf.parse(expiry);
		} catch (ParseException e) {
			throw new InvalidExpiryDateException("Invalid date - " + expiry);
		}
		return expiryDate;
	}
	
	@RequestMapping(value="/otp-list/_batch", method=RequestMethod.POST,  params={"expires", "batch-size"}) 
	public @ResponseBody OtpListBatchForCreateBatch generateBatchOtpWithExpiryDate(@RequestParam("expires") String expires, @RequestParam("batch-size") String batchSize) throws InvalidRequestParameterException {
		return generateBatchOtp(expires, batchSize);
	}
	
	@RequestMapping(value="/otp-list/_batch", method=RequestMethod.POST,  params={"max-age", "batch-size"}) 
	public @ResponseBody OtpListBatchForCreateBatch generateBatchOtpWithMaxAge(@RequestParam("max-age") String expires, @RequestParam("batch-size") String batchSize) throws InvalidRequestParameterException {
		return generateBatchOtp(expires, batchSize);
	}
	
	private OtpListBatchForCreateBatch generateBatchOtp(String expires, String batchSize) throws InvalidRequestParameterException {
		try {
			Date expiryDate = parseExpiryDate(expires);
			Integer size = Integer.parseInt(batchSize);
			OneTimePasswordListBatch otpsBatch = passwordService.generateBatchPasswordList(expiryDate, size);
			return otpTransformer.transformOtpListBatchForCreate(otpsBatch);
		} catch (InvalidExpiryDateException e) {
			throw new InvalidRequestParameterException("Invalid expires parameter: " + expires + ". Must be in yyyymmdd format.");
		} catch (NumberFormatException e) {
			throw new InvalidRequestParameterException("Invalid batch-size parameter: " + batchSize + ". Must be a valid integer.");
		}
	}
	
	@RequestMapping(value="/otp-list/{list}", method=RequestMethod.GET) 
	public @ResponseBody OtpListForLookupOtp lookUpOtpListStatus(@PathVariable OneTimePasswordList list) {
		return otpTransformer.transformOtpListForLookupOtp(list);
	}

	@RequestMapping(value="/otp-list/{list}", method=RequestMethod.PUT, params="uniqueID") 
	public @ResponseBody OtpListForAssociateOtpListWithUser associateOtpListWithUser(@PathVariable OneTimePasswordList list, @RequestParam("uniqueID") User user) throws InvalidRequestParameterException, OneTimePasswordListNotFreeException {
		return new OtpListForAssociateOtpListWithUser(passwordService.associateOtpListWithUser(list, user));
	}
	
	@RequestMapping(value="/otp-list/_delete", method=RequestMethod.PUT, params="uniqueID")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOtpList(@RequestParam("uniqueID") OneTimePasswordList passwordList) {
		passwordService.deleteOtpList(passwordList);
	}
}