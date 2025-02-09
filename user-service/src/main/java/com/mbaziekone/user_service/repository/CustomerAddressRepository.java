package com.mbaziekone.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.user_service.model.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

}
