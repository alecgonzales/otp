package com.onb.otp.datatransferobject;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.User;

@XmlRootElement(name="user-info")
public class UserForLookupUser {
	private String uniqueID;
	private Set<OtpListForLookupUser> otps = new LinkedHashSet<OtpListForLookupUser>();
	
	public String getUniqueID() {
		return uniqueID;
	}
	@XmlAttribute
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	public Set<OtpListForLookupUser> getOtps() {
		return otps;
	}
	@XmlElement(name="otp-list")
	public void setOtps(Set<OtpListForLookupUser> otps) {
		this.otps = otps;
	}
	
	public UserForLookupUser() {
	}
	
	public UserForLookupUser(User user) {
		this.uniqueID = user.getUsername();
		for (OneTimePasswordList passwordList : user.getPasswordLists()) {
			this.otps.add(new OtpListForLookupUser(passwordList)); 
		}
	}
}
