package com.infosys.ekart.exception;

@SuppressWarnings("serial")
public class ServerDownException extends Exception {
	public ServerDownException(String message) {
		super(message);
	}
}