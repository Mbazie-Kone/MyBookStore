package com.mbaziekone.user_service.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mbaziekone.user_service.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
	 @Query("SELECT r.name FROM UserRole ur JOIN ur.role r WHERE ur.user.username = :username")
	 Set<String> findRolesByUsername(String username);
}