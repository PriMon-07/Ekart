package com.infosys.ekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class EkartZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartZipkinServerApplication.class, args);
	}
}
