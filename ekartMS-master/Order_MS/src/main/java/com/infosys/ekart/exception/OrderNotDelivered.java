package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class OrderNotDelivered extends Exception {
	public OrderNotDelivered(String message) {
		super(message);
	}
}
