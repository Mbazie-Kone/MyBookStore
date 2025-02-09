package com.mbaziekone.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.user_service.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
