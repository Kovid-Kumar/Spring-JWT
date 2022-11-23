package com.spring.jwt.model;

import java.util.Date;

import lombok.Data;

@Data
public class TokenResponse {

	private String token;
	private Date expriration;
	private Date issuedAt;
	@Override
	public String toString() {
		return "TokenResponse [token=" + token + ", expriration=" + expriration + ", issuedAt=" + issuedAt + "]";
	}
	
	
}
