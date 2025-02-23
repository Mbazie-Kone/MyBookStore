package com.mbaziekone.catalog_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbaziekone.catalog_service.model.Product;
import com.mbaziekone.catalog_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}
	
	public Optional<Product> getProductById(Long id) {
		
		return productRepository.findById(id);
	}
	
	public List<Product> getProductsByCategory(Long categoryId) {
		
		return productRepository.findByCategoryId(categoryId);
	}
}
