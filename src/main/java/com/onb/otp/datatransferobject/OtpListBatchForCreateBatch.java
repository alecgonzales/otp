package com.onb.otp.datatransferobject;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.onb.otp.domain.OneTimePasswordList;
import com.onb.otp.domain.OneTimePasswordListBatch;

@XmlRootElement(name="batch-response")
public class OtpListBatchForCreateBatch {
	private Integer size;
	
	private Set<OtpListForCreateBatch> lists = new LinkedHashSet<OtpListForCreateBatch>();

	public Integer getSize() {
		return size;
	}
	@XmlAttribute(name="batch-size")
	public void setSize(Integer size) {
		this.size = size;
	}
	public Set<OtpListForCreateBatch> getLists() {
		return lists;
	}
	@XmlElement(name="otp-list")
	public void setLists(Set<OtpListForCreateBatch> lists) {
		this.lists = lists;
	}
	
	public OtpListBatchForCreateBatch() {
	}
	
	public OtpListBatchForCreateBatch(OneTimePasswordListBatch batch) {
		this.size = batch.getBatchSize();
		for(OneTimePasswordList passwordList : batch.getPasswordLists()) {
			this.lists.add(new OtpListForCreateBatch(passwordList));
		}
	}
}
