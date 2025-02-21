package com.mbaziekone.customer_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
	public List<State> getStatesByCountry(Long countyId) {
		
		return stateRepository.findByCountryId(countyId);
	}
	
	// Find state by ID
	public Optional<State> getStateById(Long id) {
		
		return stateRepository.findById(id);
	}
	
	// Create
	public State createState(State state) {
		
		return stateRepository.save(state);
	}
	
	// Delete
	public void deleteState(Long id) {
		stateRepository.deleteById(id);
	}
}
