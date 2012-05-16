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
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@Column(name="username")
	private String username;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<OneTimePasswordList> passwordLists;
	
	public Long getId() {
		return id;
	}
	@XmlTransient
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	@XmlAttribute(name="uniqueID")
	public void setUsername(String username) {
		this.username = username;
	}
	public List<OneTimePasswordList> getPasswordLists() {
		return passwordLists;
	}
	@XmlTransient
	public void setPasswordLists(List<OneTimePasswordList> passwordLists) {
		this.passwordLists = passwordLists;
	}
}
