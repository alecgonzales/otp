package com.onb.otp.domain;

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
	private Set<OneTimePasswordList> passwordLists;
	
	@ManyToOne
    @JoinColumn(name="status_id")
    private Status status; 

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<OneTimePasswordList> getPasswordLists() {
		return passwordLists;
	}
	public void setPasswordLists(Set<OneTimePasswordList> passwordLists) {
		this.passwordLists = passwordLists;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
