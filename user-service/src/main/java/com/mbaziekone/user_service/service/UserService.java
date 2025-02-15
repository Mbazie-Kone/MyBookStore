package com.mbaziekone.user_service.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mbaziekone.user_service.model.User;
import com.mbaziekone.user_service.model.UserRole;
import com.mbaziekone.user_service.repository.UserRepository;
import com.mbaziekone.user_service.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		Set<String> roles = userRepository.findRolesByUsername(username);
		
		return org.springframework.security.core.userdetails.User.builder()
			.username(user.getUsername())
			.password(user.getPassword())
			.authorities(roles.stream().map(role -> "ROLE_" + role).toArray(String[]::new))
			.build();
	}
	
	public User savedUser(User user) {
		
		return userRepository.save(user);
	}
	
	public void saveUserRole(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
}
