package com.JPA_Springboot;

import com.JPA_Springboot.service.EmpServiceImplementation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaSpringbootApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(EmpServiceImplementation empService) {
		return args -> {
			System.out.println("Seeding database with employees...");
			// Only seed if database is empty to avoid duplicates on every restart
			if (empService.findAllEmployees().isEmpty()) {
				empService.addEmployee();
				System.out.println("Database seeded successfully.");
			} else {
				System.out.println("Database already contains data, skipping seed.");
			}
		};
	}
}
