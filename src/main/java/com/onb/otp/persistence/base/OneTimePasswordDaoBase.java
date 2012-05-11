package com.onb.otp.persistence.base;

import com.onb.otp.domain.OneTimePassword;

public interface OneTimePasswordDaoBase {
	public void save(OneTimePassword password);
	public OneTimePassword getById(long id);
	public void update(OneTimePassword password);
}
