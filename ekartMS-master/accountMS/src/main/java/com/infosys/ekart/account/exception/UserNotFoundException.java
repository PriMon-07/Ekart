package com.infosys.ekart.account.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends AccountServiceException {

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}