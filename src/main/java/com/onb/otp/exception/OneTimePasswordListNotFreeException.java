package com.onb.otp.exception;

public class OneTimePasswordListNotFreeException extends RuntimeException {
	private static final long serialVersionUID = -170601920695966319L;

	public OneTimePasswordListNotFreeException(String message) {
		super(message);
	}
}
