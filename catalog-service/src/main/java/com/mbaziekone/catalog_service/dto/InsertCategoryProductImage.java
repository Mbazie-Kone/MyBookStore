package com.mbaziekone.catalog_service.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class InsertCategoryProductImage {
		
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
	
	private List<ImageDto> imageUrls;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ImageDto {
		private String imageUrl;
	}
}
