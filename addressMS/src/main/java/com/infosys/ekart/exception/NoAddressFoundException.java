package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class NoAddressFoundException extends AccountServiceException{

	public NoAddressFoundException(String errorMessage) {
		super(errorMessage);
	}
}
