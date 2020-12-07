package com.infosys.ekart.constants;

public class ApplicationConstants {
	
	public static final String CARD_CONTROLLER = "/{userId}/cards";
	public static final String CARD_CONTROLLER_CARDID = "/{userId}/cardId";
	public static final String DELETE_CARD_URL = "/{userid}/card/{cardnumber}/delete";	
	public static final String DATA = "data";
	public static final String ADD_CARD_URL = "/{userid}/card/add";
	public static final String ALL_FIELDS_ARE_REQUIRED = "All fields are required";
	public static final String INVALID_USER = "Invalid User";
	public static final String CARD_PATTERN = "\\d{13,16}";
	public static final String INVALID_CARD = "Invalid Card Number";
	public static final String CARD_ALREADY_EXISTS = "Card details already exist";
	public static final String CARD_EXPIRED = "Card expired";
	public static final String ADD_CARD_SUCCESS = "Card details added successfully";
	public static final String INVALID_DATE = "Invalid Expiry Date";
}
