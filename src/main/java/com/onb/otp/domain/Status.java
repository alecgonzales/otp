package com.onb.otp.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="status")
public class Status {
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@Column(name="value")
	private String value;
	
	@Column(name="remaining")
	private Integer remaining;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	@Cascade({CascadeType.SAVE_UPDATE})
    private List<User> users; 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	@Cascade({CascadeType.SAVE_UPDATE})
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
	public Integer getRemaining() {
		return remaining;
	}
	@XmlAttribute(name="remaining")
	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}
	public List<User> getUsers() {
		return users;
	}
	@XmlElement
	public void setUserLists(List<User> users) {
		this.users = users;
	}
	public List<OneTimePasswordList> getPasswordLists() {
		return passwordLists;
	}
	@XmlTransient
	public void setPasswordLists(List<OneTimePasswordList> passwordLists) {
		this.passwordLists = passwordLists;
	}
}
