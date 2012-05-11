package com.onb.otp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onb.otp.service.impl.PasswordService;

@Controller
public class PasswordController {
	
	@Autowired
	PasswordService passwordService;
	
	@RequestMapping(value="/create/otp-list", method=RequestMethod.GET) 
	public void generateOtp() {
		System.out.println("weh");
	}
}
