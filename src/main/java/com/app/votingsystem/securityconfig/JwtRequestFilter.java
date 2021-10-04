package com.app.votingsystem.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.votingsystem.exception.VotingSystemException;
import com.app.votingsystem.models.UserDetialsImpl;
import com.app.votingsystem.services.JwtUtilService;
import com.app.votingsystem.services.UserDetailsServiceImpl;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtilService jwtUtilService;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String voterId = null;
		String jwt = null;
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {			
			jwt = authorizationHeader.substring(7).trim();
			
			try{
				voterId = (String) jwtUtilService.extractVoterId(jwt);
			} catch(Exception e) {
				//throw new VotingSystemException("Session Expired. Please try login again.");
			}
		}
		
		if(voterId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetialsImpl userDetials = (UserDetialsImpl) this.userDetailsServiceImpl.loadUserByUsername(voterId);
			
			if(jwtUtilService.validateToken(jwt, userDetials)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new 
						UsernamePasswordAuthenticationToken(userDetials, null, userDetials.getAuthorities());
				
				usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
