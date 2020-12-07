package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class UserNotLoggedInException extends AccountServiceException {

	public UserNotLoggedInException(String errorMsg) {
		super(errorMsg);
	}
}
