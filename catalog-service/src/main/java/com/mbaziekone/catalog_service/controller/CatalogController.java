package com.mbaziekone.catalog_service.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	// GET ALL CATEGORIES
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	// IMAGE
	@PostMapping("/upload-image")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			
			return ResponseEntity.badRequest().body("File not found");
		}
		try {
			File directory = new File(UPLOAD_DIR);
			if (!directory.exists()) {
				directory.mkdirs();
			}
		
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR + fileName);
			
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			
			String fileUrl = "/images/" + fileName;
			
			return ResponseEntity.ok(fileUrl);
		
		}catch (IOException e) {
			
			return ResponseEntity.internalServerError().body("Error");
		}
	}
	
	// INSERT NEW PRODUCT
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody InsertCategoryProductImage dto) {
		// JSON log for debug isAvailable issue
		System.out.println("DTO received: " + dto);
		System.out.println("isAvalable received: " + dto.isAvailable());
		
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
