package com.mbaziekone.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.user_service.model.User;
import com.mbaziekone.user_service.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/dashboard")
	@ResponseBody
	public String helloWorld() {
		
		return "Hello World!";
	}
	
	@PostMapping("/login")
	public User loginAdmin() {
		
		return null;
	}
}
