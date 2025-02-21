package com.mbaziekone.customer_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbaziekone.customer_service.model.Country;
import com.mbaziekone.customer_service.repository.CountryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
	
	private final CountryRepository countryRepository;
	
	// Find all countries
	public List<Country> getAllCountries() {
		
		return countryRepository.findAll();
	}
	
	// Find by ID
	public Optional<Country> getCountryById(Long id) {
		
		return countryRepository.findById(id);
	}
	
	// Create
	public Country createCountry(Country country) {
		
		return countryRepository.save(country);
	}
	
	// Delete
	public void deleteCountry(Long id) {
		countryRepository.deleteById(id);
	}
	
}
