package com.infosys.ekart.notification.exception;

@SuppressWarnings("serial")
public class ErrorOnServerException extends Exception {

	public ErrorOnServerException(String errorMessage) {
		super(errorMessage);
	}
}
