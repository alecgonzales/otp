package com.onb.otp.datatransferobject;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
}
