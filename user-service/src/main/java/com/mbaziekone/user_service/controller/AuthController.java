package com.mbaziekone.user_service.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.user_service.dto.UserDto;
import com.mbaziekone.user_service.security.JwtUtil;
import com.mbaziekone.user_service.service.RoleService;
import com.mbaziekone.user_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	private final RoleService roleService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtUtil jwtUtil;
	
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public String register(@Valid @RequestBody UserDto userDto) {
		if (userService.findByUsername(userDto.getUsername()).isPresent()) {
			
			return "Username already taken!";
		}
		
		
	}
}
