package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import com.onb.otp.domain.OneTimePassword;

public class CreateOtp {
	private Integer index;
	private String value;
	
	public Integer getIndex() {
		return index;
	}
	@XmlAttribute
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getValue() {
		return value;
	}
	@XmlValue
	public void setValue(String value) {
		this.value = value;
	}
	
	public CreateOtp() {
	}
	
	public CreateOtp(OneTimePassword otp) {
		this.index = otp.getReferenceIndex();
		this.value = otp.getCode();
	}
}
