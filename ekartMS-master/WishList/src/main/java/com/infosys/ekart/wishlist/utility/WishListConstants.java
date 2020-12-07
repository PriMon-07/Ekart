package com.infosys.ekart.wishlist.utility;

public final class WishListConstants {

	public final static String ADD_ITEM = "/{userid}/wishlist/{productname}/add";
	public final static String FETCH_ALL_ITEMS = "/{userid}/wishlist";
	public final static String REMOVE_ITEM = "/{userid}/wishlist/{productname}/remove";

	public static final String ERROR_ON_SERVER = "There was an error while removing the item from the wishlist.";
	public static final String NO_ITEMS = "No_Items";
	public static final String ITEM_ALREADY_EXIST = "Item_Exists_Already";
	public static final String ITEM_ADDED ="Item_Added";
	
	public static final String USER_NOT_LOGGED_IN ="USER_NOT_LOGGED_IN";
}
