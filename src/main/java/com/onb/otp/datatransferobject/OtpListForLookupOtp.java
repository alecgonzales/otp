package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="otp-list")
public class OtpListForLookupOtp {
	private Long id;
	private StatusForLookupOtp status;
	
	public Long getId() {
		return id;
	}
	@XmlAttribute
	public void setId(Long id) {
		this.id = id;
	}
	public StatusForLookupOtp getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(StatusForLookupOtp status) {
		this.status = status;
	}
}
