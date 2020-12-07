package com.infy.ekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EkartConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartConfigApplication.class, args);
	}

}
