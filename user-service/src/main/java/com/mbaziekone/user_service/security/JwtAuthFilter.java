package com.mbaziekone.user_service.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;
	
	public JwtAuthFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = parseJwt(request);
		if (token!= null && jwtUtil.validateToken(token)) {
			String username = jwtUtil.extractUsername(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(token, userDetails));
		}
		filterChain.doFilter(request, response);
	}
		
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			
			return headerAuth.substring(7);
		}
		
		return null;
	}

}
