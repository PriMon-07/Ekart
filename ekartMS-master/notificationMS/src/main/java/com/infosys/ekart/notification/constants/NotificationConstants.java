package com.infosys.ekart.notification.constants;

public final class NotificationConstants {

	private NotificationConstants() {
	}

	/**
	 * Constant for URI
	 */
	public static final String VIEW_NOTIFICATION_URI = "/{userId}/notifications";
	public static final String ADD_NOTIFICATION_URI = "/notifications/add";

	/**
	 * Constant for property key
	 */
	public static final String ADD_NOTIFICATION_SUCCESS = "NOTIFICATION_ADDED_SUCCESS";
	public static final String USER_NOT_LOGGED_IN = "USER_NOT_LOGGED_IN";
	public static final String ERROR_ON_SERVER = "ERROR_ON_SERVER";

}