package com.mbaziekone.catalog_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbaziekone.catalog_service.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
