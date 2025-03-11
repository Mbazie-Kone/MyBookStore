package com.mbaziekone.catalog_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbaziekone.catalog_service.model.Image;
import com.mbaziekone.catalog_service.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	public List<Image> getAllImages() {
		
		return imageRepository.findAll();
	}
	
	public List<Image> getImagesByProduct(Long productId) {
		List<Image> images = imageRepository.findByProductId(productId);>
		
		return images != null ? images : List.of();
	}
	
	public Optional<Image> getImageById(Long id) {
		
		return imageRepository.findById(id);
	}

	public Image createImage(Image image) {
		
		return imageRepository.save(image);
	}
	
	public void deleteImage(Long id) {
		imageRepository.deleteById(id);
	}
}
