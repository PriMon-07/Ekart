package com.infosys.ekart.exceptions;

@SuppressWarnings("serial")
public class UserNotLoggedInException extends Exception {

	public UserNotLoggedInException(String message) {
		super(message);
	}
	
}
