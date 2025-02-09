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
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id; //Removed @GeneratedValue, as the database will handle auto-increment

	private String firstName;

	private String lastName;

	private String email;
	
	private String username;

	private String password;
	
	private String phone;

	private LocalDateTime createdAt = LocalDateTime.now();

}
