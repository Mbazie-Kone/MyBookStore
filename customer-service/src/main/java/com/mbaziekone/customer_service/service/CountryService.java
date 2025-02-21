package com.mbaziekone.customer_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mbaziekone.customer_service.model.Country;
import com.mbaziekone.customer_service.repository.CountryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
	
	private final CountryRepository countryRepository;
	
	public List<Country> getAllCountries() {
		
		return countryRepository.findAll();
	}
}
