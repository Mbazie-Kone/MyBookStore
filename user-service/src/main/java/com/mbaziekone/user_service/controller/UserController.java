package com.mbaziekone.user_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.user_service.security.JwtUtil;
import com.mbaziekone.user_service.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/admin")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		UserDetails user = userServiceImpl.loadUserByUsername(username);
		String token = jwtUtil.generateToken(user.getUsername());
		
		return ResponseEntity.ok(Map.of("token", token));
	}
	
}
