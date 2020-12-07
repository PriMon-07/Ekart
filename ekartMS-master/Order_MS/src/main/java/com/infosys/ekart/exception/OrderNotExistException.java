package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class OrderNotExistException extends Exception {
	public OrderNotExistException(String message) {
		super(message);
	}
}