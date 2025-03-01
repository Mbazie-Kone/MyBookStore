package com.mbaziekone.catalog_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbaziekone.catalog_service.repository.CategoryRepository;
import com.mbaziekone.catalog_service.repository.ImageRepository;
import com.mbaziekone.catalog_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
}
