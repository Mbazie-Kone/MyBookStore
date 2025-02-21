package com.mbaziekone.customer_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbaziekone.customer_service.model.Customer;
import com.mbaziekone.customer_service.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private CustomerRepository customerRepository;
	
	// Find all customers
	public List<Customer> getAllCustomers() {
		
		return customerRepository.findAll();
	}
	
	// Find customer by ID
	public Optional<Customer> getCustomerById(Long id) {
		
		return customerRepository.findById(id);
	}
	
	// Find customer by email
	public Optional<Customer> getCustomerByEmail(String eamil) {
		
		return customerRepository.findByEmail(eamil);
	}
	
	// Find customers by country ID
	public List<Customer> getCustomersByCounty(Long countryId) {
		
		return customerRepository.findByCountryId(countryId);
	}
	
	// Find customers by address ID
	public List<Customer> getCustomersByAddress(Long addressId) {
		
		return customerRepository.findByAddressId(addressId);
	}
	
	// Create
	public Customer createCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}
	
	// Delete
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
	
}
