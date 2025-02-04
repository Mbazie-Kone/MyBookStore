package com.mbaziekone.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.user_service.model.User;
import com.mbaziekone.user_service.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/findall")
	public List<User> getUsers() {
		
		return userService.getUsers();
	}
	
	@PostMapping("/create")
	public User insertUser(User user) {
		
		
		
		
		return null;
	}
}
