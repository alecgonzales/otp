package com.onb.otp.datatransferobject;

import javax.xml.bind.annotation.XmlAttribute;

public class StatusForLookupUser {
	private String value;
	private String index;
	
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
}
