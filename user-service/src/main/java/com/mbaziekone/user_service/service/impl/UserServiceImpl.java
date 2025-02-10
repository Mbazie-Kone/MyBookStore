package com.mbaziekone.user_service.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mbaziekone.user_service.model.User;
import com.mbaziekone.user_service.model.UserRole;
import com.mbaziekone.user_service.repository.UserRepository;
import com.mbaziekone.user_service.repository.UserRoleRepository;
import com.mbaziekone.user_service.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		// Retrieve the user roles from the user_roles table
		List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
		Set<SimpleGrantedAuthority> authorities = userRoles.stream().map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRole().getName()))
				.collect(Collectors.toSet());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
