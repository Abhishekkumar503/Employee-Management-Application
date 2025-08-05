package com.es;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Employee Service Rest APIs",
				description = "Employee Service Rest APIs Documentation",
				version = "v1.0"
				)
		)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {
	
	@Bean
	WebClient webClient()
	{
		return WebClient.builder().build();
	}
	
	@Bean
	ModelMapper modelmapper()
	{
		return new ModelMapper();
	}

    @Bean
    RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
