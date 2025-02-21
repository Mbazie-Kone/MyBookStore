package com.mbaziekone.customer_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.customer_service.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	List<Address> findByCityId(Long cityId);
}
