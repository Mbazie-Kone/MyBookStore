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
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping("/view/products")
	public ResponseEntity<List<ViewCategoryProductImage>> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
		List<ViewCategoryProductImage> viewCategoryProductImages = products.stream().map(product -> {
			List<String> imageUrls = imageRepository.findByProductId(product.getId())
					.stream()
					.map(Image::getImageUrl)
					.collect(Collectors.toList());
			
			return new ViewCategoryProductImage(
						product.getId(),
						product.getName(),
						product.getDescription(),
						product.getPrice(),
						product.getStock(),
						product.getIsAvailable(),
						product.getCategory().getName(),
						imageUrls
					);
			}).collect(Collectors.toList());
		
		return ResponseEntity.ok(viewCategoryProductImages);
	}
	
	// VIEW SINGLE PRODUCT
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
		
		
		List<String> imageUrls = imageRepository.findByProductId(product.getId())
				.stream()
				.map(Image::getImageUrl)
				.collect(Collectors.toList());
		
		ViewCategoryProductImage productDto = new ViewCategoryProductImage(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getStock(),
				product.getIsAvailable(),
				product.getCategory().getName(),
				imageUrls
			);
		
			return ResponseEntity.ok(productDto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
	}
	
	// GET ALL CATEGORIES
	@GetMapping("view/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	// IMAGE
	@PostMapping("/upload/image")
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
	@PostMapping("/insert/product")
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
			
			// We save up to 10 images, if available.
			if (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty()) {
				int limit = Math.min(dto.getImageUrls().size(), 10); // Max 10 images
				for (int i = 0; i < limit; i++) {
					Image image = new Image();
					image.setProduct(product);
					image.setImageUrl(dto.getImageUrls().get(i));
					
					imageRepository.save(image);
				}
			}
			
			Map<String, String> response = new HashMap<>();
			response.put("messagge","Product created successfully!");
			
			return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException e) {
			
			return ResponseEntity.badRequest().body("Error: Product name must be unique");
		}
	}
	
	// UPDATE PRODUCT
	@PutMapping("/update/product/{id}")
	public ResponseEntity<Map<String, String>> updateProduct(@PathVariable Long id, @Valid @RequestBody InsertCategoryProductImage dto) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			
			// Check if the name already exists for another product
			Optional<Product> existingProduct = productRepository.findByName(dto.getName());
			if (existingProduct.isPresent() && !existingProduct.get().getId().equals(id)) {
				
				return ResponseEntity.badRequest().body(Map.of("Error", "A product with this name already exists."));
			}
			
			// Category
			Category category = categoryRepository.findByName(dto.getCategoryName()).orElseGet(() -> {
				Category newCategory = new Category();
				newCategory.setName(dto.getCategoryName());
				
				return categoryRepository.save(newCategory);
			});
			
			// Update product details
			product.setName(dto.getName());
			product.setDescription(dto.getDescription());
			product.setPrice(dto.getPrice());
			product.setStock(dto.getStock());
			product.setIsAvailable(dto.getIsAvailable());
			product.setCategory(category);
			
			productRepository.save(product);
			
			// Manage images
			List<Image> existingImages = imageRepository.findByProductId(product.getId());
			
			// Delete removed images
			List<String> newImageUrls = dto.getImageUrls();
			for (Image image : existingImages) {
				if(!newImageUrls.contains(image.getImageUrl())) {
					deleteImageFile(image.getImageUrl()); // Remove from filesystem
					
					imageRepository.delete(image); // Remove from database
				}
			}
			
			// Add new images
			for (String imageUrl : newImageUrls) {
				if (existingImages.stream().noneMatch(img -> img.getImageUrl().equals(imageUrl))) {
					Image newImage = new Image();
					newImage.setProduct(product);
					newImage.setImageUrl(imageUrl);
					
					imageRepository.save(newImage);
				}
			}
			
			// JSON response
			Map<String, String> response = new HashMap<>();
			response.put("message", "Product updated successfully!");
			
			return ResponseEntity.ok(response);
		} else {
			
			return ResponseEntity.status(404).body(Map.of("Error", "Product not found."));
		}
	}
	
	// DELETE PRODUCT
	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if (product.isPresent()) {
			
			// Retrieve all images associated with the product
			List<Image> images = imageRepository.findByProductId(id);
			
			for (Image image : images) {
				String imagePath = "../frontend/public" + image.getImageUrl();
				
				try {
					Path path = Paths.get(imagePath);
					if (Files.exists(path)) {
						Files.delete(path); // Delete the physical file
						System.out.println("Deleted image file: " + imagePath);
					}
				} catch (IOException e) {
					System.err.println("Error deleting image file: " + imagePath);
					e.printStackTrace();
				}
			}
			
			// Delete images from database
			imageRepository.deleteAllByProductId(id);
			
			productRepository.deleteById(id);
			
			// We return a valid JSON response
			Map<String, String> response = new HashMap<>();
			response.put("message","Product and associated images deleted successfully!");
			
			return ResponseEntity.ok(response);
		} else {
			Map<String, String> error = new HashMap<>();
			error.put("error","Product not found.");
			
			return ResponseEntity.status(404).body(error);
		}
	}
	
	// DELETE IMAGE BY ID
	@DeleteMapping("/delete/image/{imageId}")
	public ResponseEntity<Map<String, String>> deleteImage(@PathVariable Long imageId) {
		
	}
}
