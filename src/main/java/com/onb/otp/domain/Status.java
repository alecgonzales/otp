package com.onb.otp.domain;

import java.util.Set;

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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
	@Cascade({CascadeType.SAVE_UPDATE})
    private Set<User> users; 
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
	@Cascade({CascadeType.SAVE_UPDATE})
    private Set<OneTimePasswordList> passwordLists;

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
	public Set<User> getUsers() {
		return users;
	}
	@XmlElement(name="user-info")
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<OneTimePasswordList> getPasswordLists() {
		return passwordLists;
	}
	@XmlTransient
	public void setPasswordLists(Set<OneTimePasswordList> passwordLists) {
		this.passwordLists = passwordLists;
	}
}
