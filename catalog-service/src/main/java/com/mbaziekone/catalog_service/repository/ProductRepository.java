package com.mbaziekone.catalog_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbaziekone.catalog_service.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategoryId(Long categoryId);
	List<Product> findByIsAvailableTrue();
	Optional<Product> findByName(String name);
}
