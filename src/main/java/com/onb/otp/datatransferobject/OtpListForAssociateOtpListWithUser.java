package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="otp-list")
public class OtpListForAssociateOtpListWithUser {
	private Long id;
	private StatusForAssociateOtpListWithUser status;
	
	public Long getId() {
		return id;
	}
	@XmlAttribute
	public void setId(Long id) {
		this.id = id;
	}
	public StatusForAssociateOtpListWithUser getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(StatusForAssociateOtpListWithUser status) {
		this.status = status;
	}
}
