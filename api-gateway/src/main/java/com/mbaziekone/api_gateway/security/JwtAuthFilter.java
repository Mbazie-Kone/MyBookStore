package com.mbaziekone.api_gateway.security;

import java.security.Key;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.nio.charset.StandardCharsets;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthFilter implements WebFilter{
	
	private static final String SECRET_KEY = "mySecretKeyForJWTAuthenticationInMicroservices";

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(request);
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

            log.info("JWT valid: User = {}", claims.getSubject());

        } catch (Exception e) {
            log.error("Token JWT error!");
        }

        return chain.filter(request);
    }

}
