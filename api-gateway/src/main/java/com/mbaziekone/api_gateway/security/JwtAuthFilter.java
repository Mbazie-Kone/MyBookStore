package com.mbaziekone.api_gateway.security;

import java.net.http.HttpHeaders;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class JwtAuthFilter implements WebFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		
		if (authHeader == null || authHeader.startsWith("Bearer")) {
			
			return chain.filter(exchange);
		}
		
		String token = authHeader.substring(7);
		return null;
	}

}
