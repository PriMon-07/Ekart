package com.infosys.ekart.constant;

public final class AddressConstants {

	private AddressConstants() {
	}

	/**
	 * Constant for URI
	 */
	public static final String VIEW_ADDRESS_URI = "/{userId}/address";
	public static final String ADD_ADDRESS_URI = "/{userid}/address/add";
	public static final String VIEW_ADDRESS_TO_MODIFY_URI = "/{userid}/address/{addressid}";
	public static final String MODIFY_ADDRESS_URI = "/{userid}/address/{addressid}/modify";
	public static final String DELETE_ADDRESS_URI = "/{userid}/address/{addressid}/delete";
	public static final String DELETE_CARD_URI = "/{userid}/card/{cardnumber}/delete";

	public static final String USER_NOT_LOGGED_IN = "USER_NOT_LOGGED_IN";
}