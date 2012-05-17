package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.onb.otp.domain.Status;
import com.onb.otp.domain.User;

public class StatusForLookupOtp {
	private String value;
	private String index;
	private Integer remaining;
	private UserForLookupOtp user;
	
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
	public int getRemaining() {
		return remaining;
	}
	@XmlAttribute
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	public UserForLookupOtp getUser() {
		return user;
	}
	@XmlElement(name="user-info")
	public void setUser(UserForLookupOtp user) {
		this.user = user;
	}
	
	public StatusForLookupOtp() {
	}
	
	public StatusForLookupOtp(Status status, User user) {
		this.value = status.getValue();
		this.remaining = status.getRemaining();
		this.index = status.getReferenceIndex();
		this.user = new UserForLookupOtp(user);
	}
}
