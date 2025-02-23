package com.mbaziekone.catalog_service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mbaziekone.catalog_service.model.Category;
import com.mbaziekone.catalog_service.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories() {
		
		return categoryRepository.findAll();
	}
	
	public Optional<Category> getCategoryById(Long id) {
		
		return categoryRepository.findById(id);
	}
	
	public Category createCategory(Category category) {
		
		return categoryRepository.save(category);
	}
	
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}
