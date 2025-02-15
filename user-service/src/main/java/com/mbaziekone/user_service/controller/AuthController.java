package com.mbaziekone.user_service.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.user_service.dto.AuthRequest;
import com.mbaziekone.user_service.dto.AuthResponse;
import com.mbaziekone.user_service.dto.UserRequestDto;
import com.mbaziekone.user_service.model.Role;
import com.mbaziekone.user_service.model.User;
import com.mbaziekone.user_service.model.UserRole;
import com.mbaziekone.user_service.repository.RoleRepository;
import com.mbaziekone.user_service.security.JwtUtil;
import com.mbaziekone.user_service.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final JwtUtil jwtUtil;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request ) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userService.loadUserByUsername(request.getUsername());
		String token = jwtUtil.generateToken(user.getUsername());
		
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserRequestDto userRequestDto) {
		Optional<Role> optionalRole = roleRepository.findByName(userRequestDto.getRole());
		if (optionalRole.isEmpty()) {
			
			return ResponseEntity.badRequest().body("Error! Role not found!");
		}
		
		User user = new User();
		user.setUsername(userRequestDto.getUsername());
		user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		
		User savedUser = userService.savedUser(user);
		
		UserRole userRole = new UserRole();
		userRole.setUser(savedUser);
		userRole.setRole(optionalRole.get());
		userService.saveUserRole(userRole);
		
		return ResponseEntity.ok("Registration successfull! " + userRequestDto.getRole());
	}
	
	
}
