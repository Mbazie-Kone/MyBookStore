package com.mbaziekone.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.user_service.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
