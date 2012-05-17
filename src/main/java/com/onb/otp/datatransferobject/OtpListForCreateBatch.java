package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;

public class OtpListForCreateBatch {
	private Long id;

	public Long getId() {
		return id;
	}
	@XmlAttribute
	public void setId(Long id) {
		this.id = id;
	}
}
