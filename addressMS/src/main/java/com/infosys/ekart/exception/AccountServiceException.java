package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class AccountServiceException extends Exception {
	public AccountServiceException(String errorMessage) {
		super(errorMessage);
	}

}
