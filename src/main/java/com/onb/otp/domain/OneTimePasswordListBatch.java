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
@Table(name="one_time_password_list_batch")
public class OneTimePasswordListBatch {
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "batch")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<OneTimePasswordList> passwordLists;
	
	@Column(name="batch_size")
	private int batchSize;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<OneTimePasswordList> getPasswordLists() {
		return passwordLists;
	}
	public void setPasswordLists(Set<OneTimePasswordList> passwordLists) {
		this.passwordLists = passwordLists;
	}
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
}
