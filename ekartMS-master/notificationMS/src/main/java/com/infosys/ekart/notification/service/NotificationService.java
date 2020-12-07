package com.infosys.ekart.notification.service;

import java.util.List;

import com.infosys.ekart.notification.exception.ErrorOnServerException;
import com.infosys.ekart.notification.model.NotificationModel;

public interface NotificationService {

	List<NotificationModel> getNotification(String userId) throws ErrorOnServerException;

	void addNotification(NotificationModel notification) throws ErrorOnServerException;
}
