package com.mbaziekone.customer_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbaziekone.customer_service.model.City;
import com.mbaziekone.customer_service.repository.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {
	
	private final CityRepository cityRepository;
	
	// Find all cities
	public List<City> getAllCities() {
		
		return cityRepository.findAll();
	}
	
	// Find cities by state ID
	public List<City> getCitiesByState(Long stateId) {
		
		return cityRepository.findByStateId(stateId);
	}
	
	// Find city by ID
	public Optional<City> getCityById(Long id) {
		
		return cityRepository.findById(id);
	}
	
	// Create
	public City createCity(City city) {
		
		return cityRepository.save(city);
	}
	
	// Delete
	public void deleteState(Long id) {
		cityRepository.deleteById(id);
	}
}
