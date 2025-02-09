package com.mbaziekone.user_service.service;

import com.mbaziekone.user_service.dto.CustomerRegistrationDto;
import com.mbaziekone.user_service.model.Customer;

public interface CustomerService {
	
	public Customer registerCustomer(CustomerRegistrationDto customerRegistrationDto);
}
