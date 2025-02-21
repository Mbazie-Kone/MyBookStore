package com.mbaziekone.customer_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbaziekone.customer_service.model.Country;
import com.mbaziekone.customer_service.model.State;
import com.mbaziekone.customer_service.repository.StateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateService {
	
	private final StateRepository stateRepository;
	
	// Find all states
	public List<State> getAllCountries() {
		
		return stateRepository.findAll();
	}
	
	// Find states by country ID
	public List<State> getStateByCountry(Long countyId) {
		
		return stateRepository.findByCountryId(countyId);
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
