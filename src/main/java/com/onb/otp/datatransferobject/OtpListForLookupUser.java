package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

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
}
