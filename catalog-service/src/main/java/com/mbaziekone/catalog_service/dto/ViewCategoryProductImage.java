package com.mbaziekone.catalog_service.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCategoryProductImage {
	
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private int stock;
	private Boolean isAvailable;
	private String categoryName;
	private List<ImageDto> imageUrls;
	private String mainImageUrl;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ImageDto {
		private Long id;
		private String imageUrl;
	}
}
