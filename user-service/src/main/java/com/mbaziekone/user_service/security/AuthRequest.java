package com.mbaziekone.user_service.security;

import lombok.Data;

@Data
public class AuthRequest {
	
	private String username;
	
	private String password;
}
