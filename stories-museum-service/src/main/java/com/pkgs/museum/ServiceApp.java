package com.pkgs.museum;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApp {

	private static Logger logger = LoggerFactory.getLogger(ServiceApp.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceApp.class, args);
	}

	@PostConstruct
	public void init() {
		logger.info("PostConstruct start");
	}

}
