package com.mbaziekone.customer_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.customer_service.model.City;

public interface CityRepository extends JpaRepository<City, Long>{
	
	List<City> findByStateId(Long stateId);
}
