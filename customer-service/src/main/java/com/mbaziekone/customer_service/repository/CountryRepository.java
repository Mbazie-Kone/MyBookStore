package com.mbaziekone.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.customer_service.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
