package com.onb.otp.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement(name = "one_time_password_list")
@Entity
@Table(name="one_time_password_list")
public class OneTimePasswordList {
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "passwordList")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<OneTimePassword> passwords;
	
	@OneToOne
	private User user;
	
	@Column(name="expires")
	private Date expires;
	
	@Column(name="size")
	private int size;

	public Long getId() {
		return id;
	}
	@XmlElement
	public void setId(Long id) {
		this.id = id;
	}
	public Set<OneTimePassword> getPasswords() {
		return passwords;
	}
	@XmlElement
	public void setPasswords(Set<OneTimePassword> passwords) {
		this.passwords = passwords;
	}
	public User getUser() {
		return user;
	}
	@XmlElement
	public void setUser(User user) {
		this.user = user;
	}
	public Date getExpires() {
		return expires;
	}
	@XmlElement
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public int getSize() {
		return size;
	}
	@XmlElement
	public void setSize(int size) {
		this.size = size;
	}
}
