package com.users;
/*
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
	public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		  String username = authentication.getName();
	        String password = authentication.getCredentials()
	            .toString();

	        if ("externaluser".equals(username) && "pass".equals(password)) {
	            return new UsernamePasswordAuthenticationToken
	              (username, password, Collections.emptyList());
	        } else {
	            throw new 
	              BadCredentialsException("External system authentication failed");
	        }
		// TODO Auto-generated method stub
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		   return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
		
	}

*/