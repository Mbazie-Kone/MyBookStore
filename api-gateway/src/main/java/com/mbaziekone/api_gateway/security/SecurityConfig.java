package com.mbaziekone.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.securityMatcher("/**")
				.authorizeHttpRequests((auth) -> auth.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/customers/**").permitAll().requestMatchers("/api/admin/**")
						.hasRole("ADMIN").anyRequest().authenticated()
				)
				.csrf(csrf -> csrf.disable())
	            .cors(cors -> cors.configurationSource(request -> {
	                var config = new org.springframework.web.cors.CorsConfiguration();
	                config.setAllowedOrigins(java.util.List.of("http://localhost:4200"));
	                config.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	                config.setAllowedHeaders(java.util.List.of("Authorization", "Content-Type"));
	                config.setAllowCredentials(true);
	                return config;
	            }))
	            .build();

	}
}
