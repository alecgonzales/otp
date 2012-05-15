package com.onb.otp.exception;

public class InvalidRequestParameterException extends RuntimeException {
	
	private static final long serialVersionUID = -5807362553508135312L;

	public InvalidRequestParameterException(String message) {
		super(message);
	}
}
