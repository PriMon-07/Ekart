package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class UserNotRegisteredException extends Exception {
	public UserNotRegisteredException(String message) {
		super(message);
	}
}