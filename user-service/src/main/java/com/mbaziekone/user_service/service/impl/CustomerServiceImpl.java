package com.mbaziekone.user_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbaziekone.user_service.dto.CustomerRegistrationDto;
import com.mbaziekone.user_service.model.Customer;
import com.mbaziekone.user_service.repository.AddressRepository;
import com.mbaziekone.user_service.repository.CustomerAddressRepository;
import com.mbaziekone.user_service.repository.CustomerRepository;
import com.mbaziekone.user_service.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	
	@Override
	public Customer registerCustomer(CustomerRegistrationDto customerRegistrationDto) {
		//Let's create a new Customer
		Customer customer = new Customer();
		customer.setFirstName(customerRegistrationDto.getFirstName());
		customer.setLastName(customerRegistrationDto.getLastName());
		customer.setEmail(customerRegistrationDto.getEmail());
		//customer.setPassword;
		customer.setPhone(customerRegistrationDto.getPhone());
		
		//We save the Customer in the database
		customer = customerRepository.save(customer);
		
		
		
		
		return null;
	}

}
