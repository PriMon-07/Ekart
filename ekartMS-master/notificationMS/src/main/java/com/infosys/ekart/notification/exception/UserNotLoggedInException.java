package com.infosys.ekart.notification.exception;

@SuppressWarnings("serial")
public class UserNotLoggedInException extends Exception {

	public UserNotLoggedInException(String errorMsg) {
		super(errorMsg);
	}
}
