package com.mbaziekone.catalog_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	private static final String UPLOAD_DIR = "../frontend/public/images/";
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
	
	// Get all categories
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	// Image
	
	
	// Insert new product
	@PostMapping("/insert/product")
	public ResponseEntity<Product> addProduct(@RequestBody InsertCategoryProductImage dto) {
		// Category
		Category category = categoryRepository.findByName(dto.getCategoryName()).orElseGet(() -> {
			Category newCategory = new Category();
			newCategory.setName(dto.getCategoryName());
			
			return categoryRepository.save(newCategory);
		});
		
		// Product
		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		product.setAvailable(dto.isAvailable());
		product.setCategory(category);
		
		productRepository.save(product);
		
		// Image
		Image image = new Image();
		image.setImageUrl(dto.getImageUrl());
		image.setProduct(product);
		
		imageRepository.save(image);

		return ResponseEntity.ok(product);
	
	}
}
