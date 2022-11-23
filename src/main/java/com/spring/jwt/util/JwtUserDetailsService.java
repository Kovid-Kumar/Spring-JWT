package com.spring.jwt.util;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.jwt.dao.UserDao;
import com.spring.jwt.model.UserModel;
@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<UserModel> user=dao.getUser(username);
		 if(user.isPresent()) {
			 return new User(user.get().getUsername(), user.get().getPassword(), new ArrayList<GrantedAuthority>());
		 }else {
			 throw new UsernameNotFoundException("Username not found in database : "+username);
		 }
	}

}
