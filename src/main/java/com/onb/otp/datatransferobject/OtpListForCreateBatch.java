package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;

import com.onb.otp.domain.OneTimePasswordList;

public class OtpListForCreateBatch {
	private Long id;

	public Long getId() {
		return id;
	}
	@XmlAttribute
	public void setId(Long id) {
		this.id = id;
	}
	
	public OtpListForCreateBatch() {
	}
	
	public OtpListForCreateBatch(OneTimePasswordList passwordList) {
		this.id = passwordList.getId();
	}
}
