package com.procttor.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Project Management API"))
@SpringBootApplication
public class ApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
