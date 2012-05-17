package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;

import com.onb.otp.domain.User;

public class UserForLookupOtp {
	private String uniqueID;
	
	public String getUniqueID() {
		return uniqueID;
	}
	@XmlAttribute
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	public UserForLookupOtp() {
	}
	
	public UserForLookupOtp(User user) {
		this.uniqueID = user.getUsername();
	}
}
