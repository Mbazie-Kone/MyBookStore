package com.mbaziekone.user_service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mbaziekone.user_service.model.User;
import com.mbaziekone.user_service.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPasswordHash()).authorities(user.getRoles().getName()).build();
	}

}
