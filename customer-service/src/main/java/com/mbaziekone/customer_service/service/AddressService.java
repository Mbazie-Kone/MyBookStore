package com.mbaziekone.customer_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbaziekone.customer_service.model.Address;
import com.mbaziekone.customer_service.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
	
	private final AddressRepository addressRepository;
	
	// Find all addresses
	public List<Address> getAllAddresses() {
		
		return addressRepository.findAll();
	}
	
	// Find addresses by city ID
	public List<Address> getAddressesByCity(Long cityId) {
		
		return addressRepository.findByCityId(cityId);
	}
	
	// Find address by ID
	public Optional<Address> getAddressById(Long id) {
		
		return addressRepository.findById(id);
	}
	
	// Create
	public Address createAddress(Address address) {
		
		return addressRepository.save(address);
	}
	
	// Delete
	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}
}
