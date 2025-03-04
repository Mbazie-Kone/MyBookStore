package com.mbaziekone.catalog_service.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbaziekone.catalog_service.dto.InsertCategoryProductImage;
import com.mbaziekone.catalog_service.dto.ViewCategoryProductImage;
import com.mbaziekone.catalog_service.model.Category;
import com.mbaziekone.catalog_service.model.Image;
import com.mbaziekone.catalog_service.model.Product;
import com.mbaziekone.catalog_service.repository.CategoryRepository;
import com.mbaziekone.catalog_service.repository.ImageRepository;
import com.mbaziekone.catalog_service.repository.ProductRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {
	
	private static final String UPLOAD_DIR = "../frontend/public/images/";
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
	
	// VIEW ALL PRODUCTS
	@GetMapping("/view-products")
	public ResponseEntity<List<ViewCategoryProductImage>> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
		List<ViewCategoryProductImage> viewCategoryProductImages = products.stream().map(product -> {
			String imageUrl = imageRepository.findByProductId(product.getId()).stream().findFirst().map(Image::getImageUrl).orElse("");
			
			return new ViewCategoryProductImage(
						product.getId(),
						product.getName(),
						product.getDescription(),
						product.getPrice(),
						product.getStock(),
						product.getIsAvailable(),
						product.getCategory().getName(),
						imageUrl
					);
			}).collect(Collectors.toList());
		
		return ResponseEntity.ok(viewCategoryProductImages);
	}
	
	// VIEW SINGLE PRODUCT
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
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
			
			String fileUrl = "./images/" + fileName;
			
			return ResponseEntity.ok(fileUrl);
		
		}catch (IOException e) {
			
			return ResponseEntity.internalServerError().body("Error");
		}
	}
	
	// INSERT NEW PRODUCT
	@PostMapping("/products")
	public ResponseEntity<?> addProduct(@Valid @RequestBody InsertCategoryProductImage dto) {
		try {
			// JSON log for debug isAvailable issue
			System.out.println("DTO received: " + dto);
			System.out.println("isAvalable received: " + dto.getIsAvailable());
			
			//Category
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
			product.setIsAvailable(dto.getIsAvailable());
			product.setCategory(category);
			
			productRepository.save(product);
			
			// Image
			Image image = new Image();
			image.setImageUrl(dto.getImageUrl());
			image.setProduct(product);
			
			imageRepository.save(image);
			
			Map<String, String> response = new HashMap<>();
			response.put("messagge","Product created successfully!");
			
			return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException e) {
			
			return ResponseEntity.badRequest().body("Error: Product name must be unique");
		}
	}
	
	// DELETE PRODUCT
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if (product.isPresent()) {
			
			imageRepository.deleteAllByProductId(id);
			
			productRepository.deleteById(id);
			
			return ResponseEntity.ok("Product deleted successfully!");
		} else {
			
			return ResponseEntity.status(404).body("Error: Product not found.");
		}
	}
}
