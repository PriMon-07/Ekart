package com.infosys.ekart.account.model;

public class BaseResponse {

	private String message;
	private Integer Status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

}
