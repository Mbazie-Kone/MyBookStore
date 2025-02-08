package com.mbaziekone.user_service.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 255)
	private String street;
	
	@Column(nullable = false, length = 100)
	private String city;
	
	@Column(nullable = false, length = 100)
	private String state;
	
	@Column(nullable = false, length = 20)
	private String zipCode;
	
	@Column(nullable = false, length = 100)
	private String country;
	
}
