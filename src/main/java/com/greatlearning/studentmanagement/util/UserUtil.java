package com.greatlearning.studentmanagement.util;

import java.util.Map;

import com.greatlearning.studentmanagement.entity.Roles;
import com.greatlearning.studentmanagement.entity.Users;

public class UserUtil {

	public static String getHighestRole(Users user, Map<Integer, String> roleMap) {
		return roleMap.entrySet().stream()
				.filter(entry -> user.getRoles().stream().map(Roles::getName).toList().contains(entry.getValue()))
				.min(Map.Entry.comparingByKey()).get().getValue();
	}

}
