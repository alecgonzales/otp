package com.onb.otp.persistence.base;

import com.onb.otp.domain.Status;

public interface StatusDaoBase {
	public void save(Status status);
	public Status getById(long id);
	public void update(Status status);
	public Status getByValue(String value);
}
