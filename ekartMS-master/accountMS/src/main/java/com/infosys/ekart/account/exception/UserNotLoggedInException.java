package com.infosys.ekart.account.exception;

@SuppressWarnings("serial")
public class UserNotLoggedInException extends AccountServiceException {

	public UserNotLoggedInException(String errorMsg) {
		super(errorMsg);
	}
}
