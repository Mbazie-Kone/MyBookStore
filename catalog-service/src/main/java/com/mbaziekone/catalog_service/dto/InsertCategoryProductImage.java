package com.mbaziekone.catalog_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertCategoryProductImage {
	
	@NotBlank(message = "Product name is required")
	private String name;
	private String description;
	private BigDecimal price;
	private int stock;
	private Boolean isAvailable;
	private String categoryName;
	private String imageUrl;
}
