package com.os.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RefreshScope
@RestController
public class MessageController {
	
	@Value("${spring.boot.message}")
	private String message;
	
	@Operation(
			
			summary = "Get Message Rest API",
			description = "Get Message REST API is used to get message value from a SCC (spring cloud Config)")

		@ApiResponse(
			responseCode = "200",
			description = "HTTP status 200 SUCCESS" 
			)
	@GetMapping("user/message")
	public String message() {
		return message;
	}
	
@Operation(
			
			summary = "Rerfresh Project Rest API",
			description = "Refresh Project after change in SCC (spring cloud Config)")

		@ApiResponse(
			responseCode = "200",
			description = "HTTP status 200 SUCCESS" 
			)
	@PostMapping("actuator/busrefresh")
	public void refreshActuator() {}
}
