package com.spring.jwt.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.spring.jwt.model.TokenResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class TokenManager{

	private String jwtSecret;
	private int tokenValidity=1000;
	private String signature="7336763979244226452948404D6351665468576D5A7134743777217A25432A46";
	
	public TokenResponse generateJwtToken(UserDetails user) {
		TokenResponse response=new TokenResponse();
		Map<String, Object> claims=new HashMap<String, Object>();
		
		Date issuedAt=new Date(System.currentTimeMillis());
		Date expriration=new Date(System.currentTimeMillis()+tokenValidity*1000);
		jwtSecret=Jwts.builder().setClaims(claims)//.setId("A234")
				.setSubject(user.getUsername()).setIssuer(user.getUsername())
				.setIssuedAt(issuedAt)
				.setExpiration(expriration)
				.signWith(Keys.hmacShaKeyFor(Base64.getEncoder().encode(signature.getBytes())),SignatureAlgorithm.HS256)
				.compact();
		response.setExpriration(expriration);
		response.setIssuedAt(issuedAt);
		response.setToken(jwtSecret);
		return response;
	}
	
	public boolean validateJwtToken(String token, UserDetails user) {
		
		Claims claims=Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(Base64.getEncoder().encode(signature.getBytes()))).parseClaimsJws(token).getBody();
		String username=claims.getSubject();
		boolean isTokenExpired=claims.getExpiration().before(new Date());
		return (username.equals(user.getUsername()) && !isTokenExpired);
	}

	public String getUsernameFromToken(String token) {
		Claims claims=Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(Base64.getEncoder().encode(signature.getBytes()))).parseClaimsJws(token).getBody();
		String username=claims.getSubject();
		return username;
	}
	public static void main(String[] args) {
		TokenManager m=new TokenManager();
		UserDetails user=new User("A", "$2a$10$oDEaPrUp0KoHIQilOIaT9.u87Byz0r6K70f3NG28OPwdz0kPuZqVm",
				new ArrayList<GrantedAuthority>());
		TokenResponse r=m.generateJwtToken(user);
		System.out.println(r);
		System.out.println(m.getUsernameFromToken(r.getToken()));
		System.out.println(m.validateJwtToken(r.getToken(), user));
		
	}
}
