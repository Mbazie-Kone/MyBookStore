package com.mbaziekone.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.customer_service.model.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

}
