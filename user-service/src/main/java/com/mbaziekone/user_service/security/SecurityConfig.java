package com.mbaziekone.user_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;
	private final CustomUserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(CsrfConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/auth/**", "/api/products", "/api/cart/**").permitAll() //Public endpoints: always accessible
						.requestMatchers("/api/orders/checkout").authenticated() //Endpoints for authenticated users
						.requestMatchers("/api/admin/**").hasRole("ADMIN") //Endpoints reserved for administrators only
						.anyRequest().authenticated() //All other requests must be notarized
				)	
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		
		AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		return authManagerBuilder.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
}
