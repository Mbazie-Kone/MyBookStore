package com.mbaziekone.user_service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mbaziekone.user_service.repository.UserRepository;
import com.mbaziekone.user_service.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return userService.findByUsername(username)
				.map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
