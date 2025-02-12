package com.mbaziekone.api_gateway.security;

import java.net.http.HttpHeaders;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements WebFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String authHeader = request.getHeaders().getFirst("Authorization");
		
		if (authHeader == null || authHeader.startsWith("Bearer")) {
			
			return chain.filter(exchange);
		}
		
		String token = authHeader.substring(7);
		
		
		return chain.filter(exchange);
	}

}
