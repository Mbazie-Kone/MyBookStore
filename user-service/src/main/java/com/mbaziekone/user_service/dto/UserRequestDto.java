package com.mbaziekone.user_service.dto;

import lombok.Data;

@Data
public class UserRequestDto {
	
	private String username;
	private String password;
	private String role;
}
