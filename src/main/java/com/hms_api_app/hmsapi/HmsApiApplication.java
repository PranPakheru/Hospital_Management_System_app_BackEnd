package com.hms_api_app.hmsapi;


import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

// @SpringBootApplication
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class HmsApiApplication {

	public static void main(String[] args) {
		//below codes are to set the properties for .env
		//hid the code vision inlay hints.
		Dotenv dotenv = Dotenv.load();

		//for database configuration
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		//for special key
		System.setProperty("SPECIAL_KEY", dotenv.get("SPECIAL_KEY"));


		SpringApplication.run(HmsApiApplication.class, args);
	}


}
