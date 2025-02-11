package com.mbaziekone.user_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mbaziekone.user_service.service.impl.UserServiceImpl;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(CsrfConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/admin/login").permitAll()
						.anyRequest().authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	public UserDetailsService userDetailsService() {
		
		return userServiceImpl;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userServiceImpl);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
}
