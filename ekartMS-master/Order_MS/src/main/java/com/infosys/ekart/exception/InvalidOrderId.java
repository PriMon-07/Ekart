package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class InvalidOrderId extends Exception {
	public InvalidOrderId(String message) {
		super(message);
	}
}
