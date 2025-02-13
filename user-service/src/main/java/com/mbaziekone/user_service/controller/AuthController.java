package com.mbaziekone.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.user_service.dto.AuthRequest;
import com.mbaziekone.user_service.dto.AuthResponse;
import com.mbaziekone.user_service.security.JwtUtil;
import com.mbaziekone.user_service.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	private UserService userService;
	private JwtUtil jwtUtil;
	

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request ) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userService.loadUserByUsername(request.getUsername());
		String token = jwtUtil.generateToken(user.getUsername());
		
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testAuth() {
		
		return ResponseEntity.ok("Authentication ok");
	}
	
}
