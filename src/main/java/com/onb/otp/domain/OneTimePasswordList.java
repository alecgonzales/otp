package com.onb.otp.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="one_time_password_list")
public class OneTimePasswordList {
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "passwordList")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<OneTimePassword> passwords;
	
	@ManyToOne
    @JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name="batch_id")
    private OneTimePasswordListBatch batch; 
	
	@ManyToOne
    @JoinColumn(name="status_id")
	private Status status;
	
	@Column(name="expires")
	private Date expires;
	
	@Column(name="size")
	private int size;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<OneTimePassword> getPasswords() {
		return passwords;
	}
	public void setPasswords(Set<OneTimePassword> passwords) {
		this.passwords = passwords;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public OneTimePasswordListBatch getBatch() {
		return batch;
	}
	public void setBatch(OneTimePasswordListBatch batch) {
		this.batch = batch;
	}
	public boolean isFree() {
		return "free".equals(this.status.getValue());
	}
}
