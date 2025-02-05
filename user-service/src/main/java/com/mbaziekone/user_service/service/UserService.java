package com.mbaziekone.user_service.service;

import java.util.List;
import java.util.Optional;

import com.mbaziekone.user_service.model.User;

public interface UserService {
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	public List<User> getUsers();
	
	public User createUser(User user);

}
