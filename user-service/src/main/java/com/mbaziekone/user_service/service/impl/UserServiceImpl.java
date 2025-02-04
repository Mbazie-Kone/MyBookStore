package com.mbaziekone.user_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbaziekone.user_service.model.User;
import com.mbaziekone.user_service.repository.UserRepository;
import com.mbaziekone.user_service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		
		return userRepository.save(user);
	}

}
