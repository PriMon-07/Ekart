package com.infosys.ekart.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@EnableFeignClients
@PropertySource("classpath:message.properties")
public class NotificationMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationMsApplication.class, args);
	}
}
