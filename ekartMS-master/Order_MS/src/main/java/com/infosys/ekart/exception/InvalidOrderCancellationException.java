package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class InvalidOrderCancellationException extends Exception {
	public InvalidOrderCancellationException(String message) {
		super(message);
	}
}