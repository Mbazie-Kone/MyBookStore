package com.mbaziekone.user_service.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	//Method to get the key from the secret
	private Key getSigningKey() {
		
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	//Token generation with the new secure signature
	public String generateToken(String username) {
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSigningKey(), SignatureAlgorithm.HS512)
				.compact();
	}
	
	//Extraction of the Username from the Token
	public String extractUsername(String token) {
		
		return getClaims(token).getSubject();
	}
	
	//Token validation
	public boolean validateToken(String token) {
		try {
			getClaims(token);
			
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			
			return false;
		}
	}
	
	//Claims extraction
	private Claims getClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
