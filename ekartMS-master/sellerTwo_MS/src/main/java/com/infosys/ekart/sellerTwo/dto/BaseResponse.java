package com.infosys.ekart.sellerTwo.dto;

public class BaseResponse {
	private String message;
	private Integer statusCode;
	
	public BaseResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public BaseResponse(String message, Integer statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
