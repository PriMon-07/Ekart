package com.infosys.ekart.wishlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableDiscoveryClient
@PropertySource(value = { "classpath:configuration.properties" })
public class WishListApplication {
	
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(WishListApplication.class, args);
	}
}
