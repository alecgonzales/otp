package com.onb.otp.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Status {
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@Column(name="value")
	private String value;
	
	@Column(name="index")
	private String index;
	
	@Column(name="remaining")
	private int remaining;

	@ManyToOne
    @JoinColumn(name="user_id")
    private List<User> userLists; 
	
	@ManyToOne
    @JoinColumn(name="password_list_id")
    private List<OneTimePasswordList> passwordLists;

	public Long getId() {
		return id;
	}
	@XmlTransient
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	@XmlAttribute(name="value")
	public void setValue(String value) {
		this.value = value;
	}
	public String getIndex() {
		return index;
	}
	@XmlAttribute(name="index")
	public void setIndex(String index) {
		this.index = index;
	}
	public int getRemaining() {
		return remaining;
	}
	@XmlAttribute(name="remaining")
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	public List<User> getUserLists() {
		return userLists;
	}
	@XmlElement
	public void setUserLists(List<User> userLists) {
		this.userLists = userLists;
	}
	public List<OneTimePasswordList> getPasswordLists() {
		return passwordLists;
	}
	@XmlTransient
	public void setPasswordLists(List<OneTimePasswordList> passwordLists) {
		this.passwordLists = passwordLists;
	}
}
