package com.spring.jwt.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
		System.out.println(encode.encode("pqr"));
	}

}
