package com.onb.otp.datatransferobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.onb.otp.xml.adapter.DateAdapter;

@XmlRootElement(name="otp-list")
public class OtpListForCreate {
	private Long id;
	private Date expires;
	private int size;
	
	List<OtpForCreate> otps = new ArrayList<OtpForCreate>();
	
	public Long getId() {
		return id;
	}
	@XmlAttribute
	public void setId(Long id) {
		this.id = id;
	}
	public Date getExpires() {
		return expires;
	}
	@XmlAttribute
	@XmlJavaTypeAdapter(DateAdapter.class)
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public int getSize() {
		return size;
	}
	@XmlAttribute
	public void setSize(int size) {
		this.size = size;
	}
	public List<OtpForCreate> getOtps() {
		return otps;
	}
	@XmlElementWrapper(name="sequence")
	@XmlElement(name="otp")
	public void setOtps(List<OtpForCreate> otps) {
		this.otps = otps;
	}
}
