package com.infosys.ekart.account.exception;

@SuppressWarnings("serial")
public class EmailAreadyUsedException extends AccountServiceException {

	public EmailAreadyUsedException(String errorMessage) {
		super(errorMessage);
	}
}
