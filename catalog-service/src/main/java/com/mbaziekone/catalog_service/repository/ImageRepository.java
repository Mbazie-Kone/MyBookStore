package com.mbaziekone.catalog_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mbaziekone.catalog_service.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findFirstByProductId(Long productId);
	List<Image> findByProductId(Long productId);
	
	@Transactional
	void deleteAllByProductId(Long productId);
}
