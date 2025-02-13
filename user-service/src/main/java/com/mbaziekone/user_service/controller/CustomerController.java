package com.mbaziekone.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.user_service.dto.CustomerRegistrationDto;
import com.mbaziekone.user_service.model.Customer;
import com.mbaziekone.user_service.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	
	public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerRegistrationDto customerRegistrationDto) {
		Customer customer = customerService.registerCustomer(customerRegistrationDto);
		
		return ResponseEntity.ok(customer);
	}
}
