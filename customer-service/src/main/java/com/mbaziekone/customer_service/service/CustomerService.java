package com.mbaziekone.customer_service.service;

import org.springframework.stereotype.Service;

import com.mbaziekone.customer_service.repository.CustomerAddressRepository;
import com.mbaziekone.customer_service.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	private final AddressRepository addressRepository;
	private final CustomerAddressRepository customerAddressRepository;
}
