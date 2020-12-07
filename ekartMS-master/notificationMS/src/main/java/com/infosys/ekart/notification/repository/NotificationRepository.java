package com.infosys.ekart.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ekart.notification.entity.NotificationEntity;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

	List<NotificationEntity> findByUserIdAndReadStatus(String userId, String readStatus);

}
