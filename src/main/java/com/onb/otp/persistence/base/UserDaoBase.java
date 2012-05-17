package com.onb.otp.persistence.base;

import com.onb.otp.domain.User;

public interface UserDaoBase {
	public void save(User user);
	public User getById(long id);
	public void update(User user);
	public User getByUsername(String username);
}
