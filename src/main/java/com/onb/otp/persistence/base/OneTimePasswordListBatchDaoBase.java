package com.onb.otp.persistence.base;

import com.onb.otp.domain.OneTimePasswordListBatch;

public interface OneTimePasswordListBatchDaoBase {
	public void save(OneTimePasswordListBatch passwordListBatch);
	public OneTimePasswordListBatch getById(long id);
	public void update(OneTimePasswordListBatch passwordListBatch);
}
