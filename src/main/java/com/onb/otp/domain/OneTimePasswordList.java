package com.onb.otp.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement(name = "otp-list")
@Entity
@Table(name="one_time_password_list")
public class OneTimePasswordList {
	public enum Status {
		FREE, ASSOCIATED, ACTIVE;
	}
	
	public OneTimePasswordList() {
		this.status = Status.FREE;
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "passwordList")
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<OneTimePassword> passwords;
	
	@OneToOne
	private User user;
	
	@ManyToOne
    @JoinColumn(name="batch_id")
    private OneTimePasswordListBatch batch; 
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name="expires")
	private Date expires;
	
	@Column(name="size")
	private int size;

	public Long getId() {
		return id;
	}
	@XmlAttribute(name="id")
	public void setId(Long id) {
		this.id = id;
	}
	public List<OneTimePassword> getPasswords() {
		return passwords;
	}
	@XmlElementWrapper(name="sequence")
	@XmlElement(name="otp")
	public void setPasswords(List<OneTimePassword> passwords) {
		this.passwords = passwords;
	}
	public User getUser() {
		return user;
	}
	@XmlTransient
	public void setUser(User user) {
		this.user = user;
	}
	public Status getStatus() {
		return status;
	}
	@XmlAttribute(name="status")
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getExpires() {
		return expires;
	}
	@XmlAttribute(name="expires")	
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public int getSize() {
		return size;
	}
	@XmlAttribute(name="size")
	public void setSize(int size) {
		this.size = size;
	}
	public OneTimePasswordListBatch getBatch() {
		return batch;
	}
	public void setBatch(OneTimePasswordListBatch batch) {
		this.batch = batch;
	}
}
