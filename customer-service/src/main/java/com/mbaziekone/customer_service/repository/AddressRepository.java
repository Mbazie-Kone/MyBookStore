package com.mbaziekone.customer_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbaziekone.customer_service.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	List<Address> findByCityId(Long cityId);
}
