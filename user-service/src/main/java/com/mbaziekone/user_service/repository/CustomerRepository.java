package com.mbaziekone.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.user_service.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
