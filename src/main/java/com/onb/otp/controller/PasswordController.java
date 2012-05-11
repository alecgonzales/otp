package com.onb.otp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PasswordController {
	
	@RequestMapping(value="/create/otp-list", method=RequestMethod.GET) 
	public void generateOtp() {
		System.out.println("here");
	}
}
