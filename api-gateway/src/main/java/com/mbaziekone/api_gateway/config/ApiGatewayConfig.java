package com.mbaziekone.api_gateway.config;

import java.util.List;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
public class ApiGatewayConfig {
	
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		
		return builder.routes()
				.route("user-service", r -> r.path("/api/auth/**", "/api/customers/**", "/api/admin/**")
				.uri("http://localhost:8081"))
				.build();
	}
	
	
	public CorsWebFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowedOrigins(List.of("http://localhost:4200"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		config.setAllowCredentials(true);
		
		source.registerCorsConfiguration("/**", config);
		
		return new CorsWebFilter(source);
	}
}
