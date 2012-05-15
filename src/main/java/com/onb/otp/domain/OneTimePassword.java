package com.onb.otp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name="one_time_password")
public class OneTimePassword {
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	
	@Column(name="code")
	private String code;
	
	@Column(name="reference_index")
	private int referenceIndex;

	@ManyToOne
    @JoinColumn(name="password_list_id")
    private OneTimePasswordList passwordList; 

	public OneTimePassword() {}
	
	public OneTimePassword(int referenceIndex, String code) {
		this.referenceIndex = referenceIndex;
		this.code = code;
	}
	
	public Long getId() {
		return id;
	}
	@XmlTransient
	public void setId(Long id) {
		this.id = id;
	}
	public int getReferenceIndex() {
		return referenceIndex;
	}

	@XmlAttribute(name="index")
	public void setReferenceIndex(int referenceIndex) {
		this.referenceIndex = referenceIndex;
	}
	public String getCode() {
		return code;
	}
	
	@XmlElement
	public void setCode(String code) {
		this.code = code;
	}
	public OneTimePasswordList getPasswordList() {
		return passwordList;
	}
	public void setPasswordList(OneTimePasswordList passwordList) {
		this.passwordList = passwordList;
	}
	
}
