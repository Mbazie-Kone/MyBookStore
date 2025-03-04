package com.mbaziekone.catalog_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertCategoryProductImage {
	
	private Long id;
	
	@NotBlank(message = "Product name is required")
	private String name;
	
	@NotBlank(message = "Description is required")
	private String description;
	
	@NotNull(message = "Price is required")
	@Positive(message = "Price must be greater than zero")
	private BigDecimal price;
	
	@NotNull(message = "Stock is required")
	@Min(value = 0, message = "Stock cannot be negative")
	private int stock;
	
	@NotNull(message = "Availability is required")
	private Boolean isAvailable;
	
	@NotBlank(message = "Category name is required")
	private String categoryName;
	
	@NotBlank(message = "Image URL is required")
	private String imageUrl;
	
	/*
	 * public InsertCategoryProductImage(Long id, String name, String description,
	 * BigDecimal price, Boolean isAvailable, String categoryName, String imageUrl)
	 * { this.id = id; this.name = name; this.description = description; this.price
	 * = price; this.isAvailable = isAvailable; this.categoryName = categoryName;
	 * this.imageUrl = imageUrl; }
	 */
}
