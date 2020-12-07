package com.infosys.ekart.notification.serviceimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.infosys.ekart.notification.entity.NotificationEntity;
import com.infosys.ekart.notification.exception.ErrorOnServerException;
import com.infosys.ekart.notification.model.NotificationModel;
import com.infosys.ekart.notification.repository.NotificationRepository;
import com.infosys.ekart.notification.service.NotificationService;
import static com.infosys.ekart.notification.constants.NotificationConstants.ERROR_ON_SERVER;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository repository;
	@Autowired
	private Environment env;

	@Override
	public List<NotificationModel> getNotification(String userId) throws ErrorOnServerException {
		try {
			List<NotificationModel> notifications = new ArrayList<NotificationModel>();
			List<NotificationEntity> entities = repository.findByUserIdAndReadStatus(userId, "unread");
			if (entities.isEmpty()) {
				return notifications;
			} else {
				entities.forEach(x -> {
					NotificationModel notification = new NotificationModel();
					try {
						BeanUtils.copyProperties(notification, x);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
					notifications.add(notification);
				});
				entities.forEach(x -> {
					x.setReadStatus("read");
				});
				repository.saveAll(entities);
				return notifications;
			}
		} catch (DataAccessException exception) {
			throw new ErrorOnServerException(env.getProperty(ERROR_ON_SERVER));
		}
	}

	@Override
	public void addNotification(NotificationModel notification) throws ErrorOnServerException {
		try {
			NotificationEntity notificationEntity = new NotificationEntity();
			BeanUtils.copyProperties(notificationEntity, notification);
			repository.saveAndFlush(notificationEntity);
		} catch (DataAccessException | IllegalAccessException | InvocationTargetException exception) {
			throw new ErrorOnServerException(env.getProperty(ERROR_ON_SERVER));
		}

	}
}
