package com.onb.otp.exception;

public class InvalidExpiryDateException extends Exception {
	
	private static final long serialVersionUID = 4476179644088379289L;

	public InvalidExpiryDateException(String message) {
		super(message);
	}
}
