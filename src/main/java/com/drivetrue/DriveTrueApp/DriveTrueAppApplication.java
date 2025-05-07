package com.drivetrue.DriveTrueApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.UUID;

@SpringBootApplication
public class DriveTrueAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriveTrueAppApplication.class, args);

		String uuid = UUID.randomUUID().toString();
	}

}
