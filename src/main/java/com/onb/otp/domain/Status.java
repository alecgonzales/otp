package com.onb.otp.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(name="reference_index")
	private String referenceIndex;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
	@Cascade({CascadeType.SAVE_UPDATE})
    private Set<User> users; 
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
	@Cascade({CascadeType.SAVE_UPDATE})
    private Set<OneTimePasswordList> passwordLists;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getRemaining() {
		return remaining;
	}
	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}
	public String getReferenceIndex() {
		return referenceIndex;
	}
	public void setReferenceIndex(String referenceIndex) {
		this.referenceIndex = referenceIndex;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<OneTimePasswordList> getPasswordLists() {
		return passwordLists;
	}
	public void setPasswordLists(Set<OneTimePasswordList> passwordLists) {
		this.passwordLists = passwordLists;
	}
}
