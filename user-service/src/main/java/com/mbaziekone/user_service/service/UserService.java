package com.mbaziekone.user_service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.mbaziekone.user_service.model.User;

@Service
public class UserService implements UserDetailsService{
	
	public User loadUserByUsername(String username) {
		
		return null;
	}
}
