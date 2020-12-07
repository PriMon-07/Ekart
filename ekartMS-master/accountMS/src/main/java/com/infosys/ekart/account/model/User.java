package com.infosys.ekart.account.model;

import static com.infosys.ekart.account.constants.AccountConstants.EMPTY_NAME;
import static com.infosys.ekart.account.constants.AccountConstants.INVALID_NAME_FORMAT;
import static com.infosys.ekart.account.constants.AccountConstants.EMPTY_PASSWORD;
import static com.infosys.ekart.account.constants.AccountConstants.INVALID_PASSWORD_FORMAT;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class User {

	String userId;

	@NotBlank(message = EMPTY_NAME)
	@Pattern(regexp = "^[a-zA-Z ]{1,40}$", message = INVALID_NAME_FORMAT)
	String name;

	@NotBlank(message = EMPTY_PASSWORD)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!.])(?=\\S+$).{5,}$", message = INVALID_PASSWORD_FORMAT)
	String password;

	String accountType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
