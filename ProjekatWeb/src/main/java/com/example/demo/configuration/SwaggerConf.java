package com.example.demo.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConf {

	@Bean
	GroupedOpenApi controllerApi() {
		return GroupedOpenApi.builder()
				.group("controller-api")
				.packagesToScan("com.example.demo.controllersRest")
				.build();
	}
	
}
