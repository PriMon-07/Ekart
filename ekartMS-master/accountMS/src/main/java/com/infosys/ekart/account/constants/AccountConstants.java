package com.infosys.ekart.account.constants;

public final class AccountConstants {

	private AccountConstants() {
	}

	/**
	 * Constant for URI
	 */
	public static final String SIGNUP_URI = "/signup";
	public static final String UPDATE_DETAIL_URI = "/{userId}/update";
	public static final String VALIDATE_USER_URI = "{userId}/checkuser";

	/**
	 * Property key constant
	 */
	public static final String USER_REGISTRED_SUCCESSFULLY = "USER_REGISTRED_SUCCESSFULLY";
	public static final String DETAIL_UPDATED_SUCCESSFULLY = "DETAIL_UPDATED_SUCCESSFULLY";
	public static final String EMAIL_ALREADY_REGISTRED = "EMAIL_ALREADY_REGISTRED";
	public static final String ERROR_ON_SERVER = "ERROR_ON_SERVER";
	public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
	public static final String USER_NOT_LOGGED_IN = "USER_NOT_LOGGED_IN";

	/**
	 * Bean validation constant
	 */

	public static final String EMPTY_NAME = "User name can't be empty";
	public static final String INVALID_NAME_FORMAT = "Inavlid name!! Name can only contain character";
	public static final String EMPTY_PASSWORD = "Password can't be empty";
	public static final String INVALID_PASSWORD_FORMAT = "Invalid password1 Password must be alpha numeric with one special character";
}