package com.mahindra.finance.jsonwebtoken.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	
	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestHeader = request.getHeader("Authorization");
		logger.info("Header : {}", requestHeader);
		String username = null; 
		String token = null; 
		
		if(requestHeader != null && requestHeader.startsWith("Bearer"))
		{
			token = requestHeader.substring(7);
			logger.info("Token : {}", token);
			try {
				username = this.jwtHelper.getUserNameFromToken(token);
				logger.info("username : {}", username);
			}catch(IllegalArgumentException e) {
				logger.info("Illegal Argument While Fetching the UserName");
				e.printStackTrace();
			}
			catch(ExpiredJwtException e) {
				logger.info("Given JWT is expired");
				e.printStackTrace();
			}
			catch(MalformedJwtException e) {
				logger.info("Some change has been done in the token, !Invalid token");
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			logger.info("Invalid header value");
		}
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// fetch userdetails from username
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			logger.info("userDetails username: {}", userDetails.getUsername());
			logger.info("userDetails password: {}", userDetails.getPassword());
			logger.info("userDetails authorities: {}", userDetails.getAuthorities());
			
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
			
			logger.info("validateToken : {}", validateToken);
			if(validateToken) {
				
				UsernamePasswordAuthenticationToken authentication
					= new UsernamePasswordAuthenticationToken(userDetails, validateToken);
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
		}else {
			logger.info("validation failed");
		}
		filterChain.doFilter(request, response);
		
		
	}

}























