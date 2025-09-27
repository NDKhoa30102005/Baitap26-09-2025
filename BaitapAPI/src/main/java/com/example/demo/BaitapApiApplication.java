package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.demo.config.StorageProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)

public class BaitapApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaitapApiApplication.class, args);
	}

}
