package com.mbaziekone.user_service.security;

import java.net.Authenticator;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthFilter jwtAuthFilter;
	
	private final CustomUserDetailsService customUserDetailsService;
	
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf().disable()
				.authorizeHttpRequests( auth -> auth
						.requestMatchers("api/auth/**").permitAll()
						.anyRequest().authenticated()
				)
	}
}
