package com.mbaziekone.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.user_service.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
