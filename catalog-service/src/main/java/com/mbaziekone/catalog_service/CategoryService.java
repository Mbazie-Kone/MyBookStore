package com.mbaziekone.catalog_service;

import org.springframework.stereotype.Service;

import com.mbaziekone.catalog_service.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
}
