package com.mbaziekone.user_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbaziekone.user_service.dto.CustomerRegistrationDto;
import com.mbaziekone.user_service.model.Address;
import com.mbaziekone.user_service.model.Customer;
import com.mbaziekone.user_service.model.CustomerAddress;
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
		
		//We check if the address already exists in the database
		Address address = addressRepository.findByStreetAndCityAndStateAndZipCodeAndCountry(
				customerRegistrationDto.getStreet(), customerRegistrationDto.getCity(), customerRegistrationDto.getState(), 
				customerRegistrationDto.getZipCode(), customerRegistrationDto.getCountry()
				).orElseGet(() -> {
					//If the address does not exist, we create it
					Address newAddress = new Address();
					newAddress.setStreet(customerRegistrationDto.getStreet());
					newAddress.setCity(customerRegistrationDto.getCity());
					newAddress.setState(customerRegistrationDto.getState());
					newAddress.setZipCode(customerRegistrationDto.getZipCode());
					newAddress.setCountry(customerRegistrationDto.getCountry());
					
					return addressRepository.save(newAddress);
				});
		
				//Let's create the relationship between Customer and Address
				CustomerAddress customerAddress = new CustomerAddress();
				customerAddress.setCustomer(customer);
				customerAddress.setAddress(address);
				customerAddressRepository.save(customerAddress);
		
		return customer;
	}
}
