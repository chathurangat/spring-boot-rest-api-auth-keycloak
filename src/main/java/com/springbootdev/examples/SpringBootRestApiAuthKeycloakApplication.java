package com.springbootdev.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class SpringBootRestApiAuthKeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiAuthKeycloakApplication.class, args);
	}

}
