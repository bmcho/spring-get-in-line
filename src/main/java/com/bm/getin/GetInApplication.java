package com.bm.getin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class GetInApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetInApplication.class, args);
	}

}
