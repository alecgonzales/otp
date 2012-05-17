package com.onb.otp.exception;

public class UserDoesNotExistException extends RuntimeException {
	private static final long serialVersionUID = -8498171704747963896L;

	public UserDoesNotExistException(String message) {
		super(message);
	}
}
