package com.onb.otp.datatransferobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.onb.otp.domain.OneTimePassword;
import com.onb.otp.domain.OneTimePasswordList;

@XmlRootElement(name="otp-list")
public class CreateOtpList {
	private Long id;
	private Date expires;
	private int size;
	
	List<CreateOtp> otps = new ArrayList<CreateOtp>();
	
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
	public List<CreateOtp> getOtps() {
		return otps;
	}
	@XmlElementWrapper(name="sequence")
	@XmlElement(name="otp")
	public void setOtps(List<CreateOtp> otps) {
		this.otps = otps;
	}
	
	public CreateOtpList() {
	}
	
	public CreateOtpList(OneTimePasswordList passwordList) {
		this.id = passwordList.getId();
		this.expires = passwordList.getExpires();
		this.size = passwordList.getSize();
		for (OneTimePassword password : passwordList.getPasswords()) {
			this.otps.add(new CreateOtp(password));
		}
	}
}
