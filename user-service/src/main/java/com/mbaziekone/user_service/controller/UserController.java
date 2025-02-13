package com.mbaziekone.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.user_service.dto.AuthRequest;
import com.mbaziekone.user_service.dto.AuthResponse;
import com.mbaziekone.user_service.security.JwtUtil;
import com.mbaziekone.user_service.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request ) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userServiceImpl.loadUserByUsername(request.getUsername());
		String token = jwtUtil.generateToken(user.getUsername());
		
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	public ResponseEntity<String> testAuth() {
		
		return ResponseEntity.ok("Authentication ok");
	}
	
}
