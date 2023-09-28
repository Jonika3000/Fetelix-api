package com.example.fetelix;

import com.example.fetelix.storage.StorageProperties;
import com.example.fetelix.storage.StorageService;
import com.example.fetelix.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FetelixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FetelixApplication.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService, SeedService seedService) {
		return (args)-> {
			try {
				storageService.init();
				seedService.seedRoleData();
				seedService.seedUserData();
			}catch(Exception ex) {
				System.out.println("Some problems "+ex.getMessage());
			}
		};
	}
}
