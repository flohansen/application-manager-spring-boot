package com.florianhansen.applicationmanager.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.florianhansen.applicationmanager.authentication.JwtAuthentication;
import com.florianhansen.applicationmanager.authentication.exception.JwtAuthenticationException;
import com.florianhansen.applicationmanager.authentication.util.JwtUtil;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private JwtUtil	jwtUtil;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String) authentication.getCredentials();
		
		if (!jwtUtil.validateToken(token))
			throw new JwtAuthenticationException("Invalid authentication token");
		
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthentication.class.equals(authentication);
	}

}
