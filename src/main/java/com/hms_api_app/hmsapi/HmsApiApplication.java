package com.hms_api_app.hmsapi;

import com.hms_api_app.hmsapi.entity.Role;
import com.hms_api_app.hmsapi.repository.RoleRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HmsApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		//below codes are to set the properties for .env
		//hid the code vision inlay hints.
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));


		SpringApplication.run(HmsApiApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepo;

	//after implementing CommandLineRunner interface we get below method.
	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepo.save(adminRole);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepo.save(userRole);
	}
}
