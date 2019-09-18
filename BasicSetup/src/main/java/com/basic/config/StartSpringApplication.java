package com.basic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com" })
@EntityScan(basePackages = { "com.basic.model" })
@EnableJpaRepositories(basePackages = "com.basic.repository")
public class StartSpringApplication extends SpringBootServletInitializer {
	 static final Logger logger = LoggerFactory.getLogger(StartSpringApplication.class);
	public static void main(String[] args) {
		logger.info("*******************Inside Application Start********************");
		
		SpringApplication.run(StartSpringApplication.class, args);
		logger.info("*******************Application Started Successfully********************");
	}

}
