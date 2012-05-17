package com.onb.otp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onb.otp.datatransferobject.UserForLookupUser;
import com.onb.otp.exception.InvalidRequestParameterException;
import com.onb.otp.service.base.UserServiceBase;

@Controller
public class UserController extends BaseController {
	@Autowired
	UserServiceBase userService;
	
	@RequestMapping(value="/user-info/_query", method=RequestMethod.GET, params="uniqueID")
	public @ResponseBody UserForLookupUser lookupUserWithUsername(@RequestParam("uniqueID") String username) throws InvalidRequestParameterException {
		return new UserForLookupUser(userService.lookupUserByUsername(username));
	}
}
