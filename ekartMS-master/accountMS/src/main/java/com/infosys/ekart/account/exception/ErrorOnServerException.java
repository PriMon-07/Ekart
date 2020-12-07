package com.infosys.ekart.account.exception;

@SuppressWarnings("serial")
public class ErrorOnServerException extends Exception {

	public ErrorOnServerException(String errorMessage) {
		super(errorMessage);
	}
}
