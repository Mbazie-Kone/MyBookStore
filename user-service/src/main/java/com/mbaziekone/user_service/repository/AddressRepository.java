package com.mbaziekone.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbaziekone.user_service.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	Optional<Address> findByStreetAndCityAndStateAndZipCodeAndCountry(String street, String city, String state, String zipCode, String country);
}
