package com.mbaziekone.user_service.dto;

import com.mbaziekone.user_service.model.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	
	@NotBlank(message = "Username is required")
	@Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
	private String username;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 6 characters long")
	private String password;
	
	private Role role;
}