package com.mbaziekone.user_service.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id; //Removed @GeneratedValue, as the database will handle auto-increment
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	private String country;
	
	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt = LocalDateTime.now();
	
}
