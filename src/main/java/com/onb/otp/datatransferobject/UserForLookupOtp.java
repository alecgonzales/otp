package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;

public class UserForLookupOtp {
	private String uniqueID;
	
	public String getUniqueID() {
		return uniqueID;
	}
	@XmlAttribute
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
}
