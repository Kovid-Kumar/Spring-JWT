package com.spring.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.model.TokenResponse;
import com.spring.jwt.model.UserModel;
import com.spring.jwt.util.JwtUserDetailsService;
import com.spring.jwt.util.TokenManager;

@RestController
@RequestMapping("/api")
public class JwtController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUserDetailsService service;
	
	@Autowired
	private TokenManager tokenManager;
	@RequestMapping(value = "/login",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public TokenResponse login(@RequestBody UserModel request) throws Exception{
		System.out.println("Login controller");
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		final UserDetails user=service.loadUserByUsername(request.getUsername());
		return tokenManager.generateJwtToken(user);
		
	}
	
}
