package com.mbaziekone.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class UserController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String helloWorld() {
		
		return "Hello World!";
	}
}
