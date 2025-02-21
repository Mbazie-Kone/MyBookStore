package com.mbaziekone.customer_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.customer_service.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByEmail(String email);
	List<Customer> findByCountryId(Long countryId);
	List<Customer> findByAddressId(Long addressId);
}
