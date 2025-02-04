package com.mbaziekone.user_service.service;

import java.util.List;

import com.mbaziekone.user_service.model.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public User createUser(User user);

}
