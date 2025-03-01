package com.mbaziekone.catalog_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.catalog_service.dto.InsertCategoryProductImage;
import com.mbaziekone.catalog_service.model.Category;
import com.mbaziekone.catalog_service.model.Image;
import com.mbaziekone.catalog_service.model.Product;
import com.mbaziekone.catalog_service.repository.CategoryRepository;
import com.mbaziekone.catalog_service.repository.ImageRepository;
import com.mbaziekone.catalog_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
	
	public ResponseEntity<Product> addProduct(@RequestBody InsertCategoryProductImage dto) {
		// Category
		Category category = categoryRepository.findByName(dto.getCategoryName()).orElseGet(() -> {
			Category newCategory = new Category();
			newCategory.setName(dto.getCategoryName());
			newCategory.setDescription(dto.getCategoryDescription());
			
			return categoryRepository.save(newCategory);
		});
		
		// Image
		Image image = new Image();
		image.setImageUrl(dto.getImageUrl());
		
		imageRepository.save(image);
		
		// Product
		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		product.setAvailable(dto.isAvailable());
		product.setCategory(category);
		
		productRepository.save(product);
		
		return ResponseEntity.ok(product);
	
	}
}
