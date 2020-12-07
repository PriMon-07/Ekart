package com.infosys.ekart.account.exception;

@SuppressWarnings("serial")
public class AccountServiceException extends Exception {

	public AccountServiceException(String errorMessage) {
		super(errorMessage);
	}
}
