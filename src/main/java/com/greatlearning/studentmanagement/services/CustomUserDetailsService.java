package com.greatlearning.studentmanagement.services;

import com.greatlearning.studentmanagement.entity.Users;

public interface CustomUserDetailsService {
	
	Users geEffectiveUser();
	
	void seEffectiveUser(Users users);


}
