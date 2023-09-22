package com.hms_api_app.hmsapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HmsApiApplication {

	public static void main(String[] args) {
		//below codes are to set the properties for .env
		//hid the code vision inlay hints.
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));


		SpringApplication.run(HmsApiApplication.class, args);
	}

}
