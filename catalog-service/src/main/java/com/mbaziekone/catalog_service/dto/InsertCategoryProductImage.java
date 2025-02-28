package com.mbaziekone.catalog_service.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertCategoryProductImage {
	
	private String name;
	private String description;
	private BigDecimal price;
	private int stock;
	private boolean isAvailable;
	private String categoryName;
	private String categoryDescription;
	private String imageUrl;
}
