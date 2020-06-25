package com.springbootdev.examples;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@Configuration
@EnableFeignClients
@SpringBootApplication
public class SpringBootRestApiAuthKeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiAuthKeycloakApplication.class, args);
	}

//	@Bean
//	Logger.Level feignLoggerLevel() {
//		return Logger.Level.FULL;
//	}
}
