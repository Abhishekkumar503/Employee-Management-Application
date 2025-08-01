package com.es.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {
	
	@Value("${spring.boot.message}")
	private String message;
	
	@GetMapping("user/message")
	public String message() {
		return message;
	}
	
	@PostMapping("actuator/busrefresh")
	public void refreshActuator() {}
}
