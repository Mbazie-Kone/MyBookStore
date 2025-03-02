package com.mbaziekone.catalog_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mbaziekone.catalog_service.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	
	List<Image> findByProductId(Long productId);
	
	@Transactional
	void deleteAllByProductId(Long productId);
}
