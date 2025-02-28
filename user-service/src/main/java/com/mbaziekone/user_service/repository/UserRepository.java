package com.mbaziekone.user_service.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mbaziekone.user_service.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
	 @Query("SELECT u.role.name FROM User u WHERE u.username = :username")
	 String findRoleByUsername(@Param("username") String username);
}