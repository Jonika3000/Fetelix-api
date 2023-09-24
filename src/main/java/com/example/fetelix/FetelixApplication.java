package com.example.fetelix;

import com.example.fetelix.services.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FetelixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FetelixApplication.class, args);
	}

}
