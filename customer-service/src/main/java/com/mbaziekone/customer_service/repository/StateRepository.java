package com.mbaziekone.customer_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.customer_service.model.State;

public interface StateRepository extends JpaRepository<State, Long> {
	
	List<State> findByCountryId(Long countryId);
}
