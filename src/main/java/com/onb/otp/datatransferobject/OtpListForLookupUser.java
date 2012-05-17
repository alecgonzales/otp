package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.onb.otp.domain.OneTimePasswordList;

public class OtpListForLookupUser {
	private Long id;
	private StatusForLookupUser status;
	
	public Long getId() {
		return id;
	}
	@XmlAttribute
	public void setId(Long id) {
		this.id = id;
	}
	public StatusForLookupUser getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(StatusForLookupUser status) {
		this.status = status;
	}
	
	public OtpListForLookupUser() {
	}
	
	public OtpListForLookupUser(OneTimePasswordList passwordList) {
		this.id = passwordList.getId();
		this.status = new StatusForLookupUser(passwordList.getStatus());
	}
}
