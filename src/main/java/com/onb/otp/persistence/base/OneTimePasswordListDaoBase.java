package com.onb.otp.persistence.base;

import com.onb.otp.domain.OneTimePasswordList;

public interface OneTimePasswordListDaoBase {
	public void save(OneTimePasswordList passwordList);
	public OneTimePasswordList getById(long id);
	public void update(OneTimePasswordList passwordList);
}
