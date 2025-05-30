package com.mbaziekone.user_service.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mbaziekone.user_service.model.User;
import com.mbaziekone.user_service.repository.UserRepository;
import com.mbaziekone.user_service.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String SECRET_KEY = "Y29tcGxleFNlY3JldEtleU9mQW1pbmltdW1MZW5ndGhGQW5kU2FmZUFsd2F5cw==";
	private final long EXPIRATION_TIME = 1000 * 60 * 60; //1 hour
	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	private final UserRepository userRepository;
	private final UserService userService;
	
	public JwtUtil(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public String generateToken(String username) {
		
		UserDetails userDetails = userService.loadUserByUsername(username);
		User user = userRepository.findByUsername(username).orElseThrow();
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRole().getName());
			
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key)
				.compact();
	}
	
	//Extraction of the Username from the Token
	public String extractUsername(String token) {
		
		return extractClaim(token, Claims::getSubject);
	}
	
	//Extraction of the expiration date from the Token
	public Date extractExpiration(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}
	
	//Token validation
	public boolean validateToken(String token) {
		
		return extractExpiration(token).after(new Date());
	}
	
	//Claims extraction
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return claimsResolver.apply(claims);
	}
	
	public boolean isTokenValid(String token, String username) {
		
		return (extractUsername(token).equals(username) && !isTokenExpired(token));
	}
	
	public boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}
}
