package com.spring.jwt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.spring.jwt.model.UserModel;

@Component
public class UserDao {

	private static List<UserModel> users=new ArrayList<UserModel>();
	static {
		users.add(new UserModel("A","$2a$10$oDEaPrUp0KoHIQilOIaT9.u87Byz0r6K70f3NG28OPwdz0kPuZqVm"));//abc
		users.add(new UserModel("X","$2a$10$UrWRrsHwKkwVkhhMBLgvTu0BkRwW5yfjpczJQutUL0PWpsSClGAyO"));//xyz
		users.add(new UserModel("P","$2a$10$zz63llrClMzlAyFw/Z1DQum20FGgqREAcBsbnB37ukpD.r350X/W."));//pqr
	}
	
	public Optional<UserModel> getUser(String username) {
		
			return users.stream().filter(u->(u.getUsername().equals(username))).findAny();
		
	}
}
