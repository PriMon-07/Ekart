package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class ErrorOnServerException extends Exception{

	public ErrorOnServerException(String errorMessage) {
		super(errorMessage);
	}
}
