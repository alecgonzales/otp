package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class StatusForAssociateOtpListWithUser {
	private String value;
	private String index;
	private UserForAssociateOtpListWithUser user;
	
	public String getValue() {
		return value;
	}
	@XmlAttribute
	public void setValue(String value) {
		this.value = value;
	}
	public String getIndex() {
		return index;
	}
	@XmlAttribute
	public void setIndex(String index) {
		this.index = index;
	}
	public UserForAssociateOtpListWithUser getUser() {
		return user;
	}
	@XmlElement(name="user-info")
	public void setUser(UserForAssociateOtpListWithUser user) {
		this.user = user;
	}
}
