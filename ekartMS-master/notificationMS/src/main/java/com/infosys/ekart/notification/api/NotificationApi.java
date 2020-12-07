package com.infosys.ekart.notification.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.notification.client.AuthenticationClient;
import com.infosys.ekart.notification.exception.ErrorOnServerException;
import com.infosys.ekart.notification.exception.UserNotLoggedInException;
import com.infosys.ekart.notification.model.BaseResponse;
import com.infosys.ekart.notification.model.NotificationModel;
import com.infosys.ekart.notification.service.NotificationService;

import static com.infosys.ekart.notification.constants.NotificationConstants.VIEW_NOTIFICATION_URI;
import static com.infosys.ekart.notification.constants.NotificationConstants.ADD_NOTIFICATION_URI;
import static com.infosys.ekart.notification.constants.NotificationConstants.ADD_NOTIFICATION_SUCCESS;
import static com.infosys.ekart.notification.constants.NotificationConstants.USER_NOT_LOGGED_IN;

@RestController
public class NotificationApi {
	@Autowired
	private NotificationService service;
	@Autowired
	private Environment env;

	@Autowired
	private AuthenticationClient authenticationClient;

	@GetMapping(VIEW_NOTIFICATION_URI)
	private ResponseEntity<List<NotificationModel>> getNotifications(@PathVariable String userId)
			throws ErrorOnServerException, UserNotLoggedInException {
		Boolean isAuthenticated = authenticationClient.authenticateUser(userId);
		if (!isAuthenticated) {
			throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
		}
		List<NotificationModel> resposne = service.getNotification(userId);
		return new ResponseEntity<List<NotificationModel>>(resposne, HttpStatus.OK);
	}

	@PostMapping(ADD_NOTIFICATION_URI)
	public ResponseEntity<BaseResponse> addNotification(@RequestBody NotificationModel notification)
			throws ErrorOnServerException, UserNotLoggedInException {
		Boolean isAuthenticated = authenticationClient.authenticateUser(notification.getUserId());
		if (!isAuthenticated) {
			throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
		}
		notification.setTimeStamp(new Date());
		service.addNotification(notification);
		return new ResponseEntity<BaseResponse>(
				new BaseResponse(env.getProperty(ADD_NOTIFICATION_SUCCESS), HttpStatus.OK.value()), HttpStatus.OK);
	}
}
