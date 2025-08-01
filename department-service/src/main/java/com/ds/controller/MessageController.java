package com.ds.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
//@RequestMapping("actuator")
public class MessageController {
	
	@Value("${spring.boot.message}")
	private String message;
	
	@GetMapping("message")
	public String message() {
		return message;
	}
	@PostMapping("actuator/busrefresh")
	public void refreshActuator() {}
}
