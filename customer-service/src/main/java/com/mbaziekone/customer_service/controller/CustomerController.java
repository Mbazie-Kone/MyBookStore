package com.mbaziekone.customer_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.customer_service.dto.CustomerRegistrationDto;
import com.mbaziekone.customer_service.model.Customer;
import com.mbaziekone.customer_service.service.CustomerService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerRegistrationDto customerRegistrationDto) {
		Customer customer = customerService.registerCustomer(customerRegistrationDto);
		
		return ResponseEntity.ok(customer);
	}
}
