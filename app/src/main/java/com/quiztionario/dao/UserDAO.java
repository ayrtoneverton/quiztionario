package com.quiztionario.dao;

import com.quiztionario.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
	private static int currentId = 0;
	private static List<User> users = new ArrayList<User>(){{
		add(new User("Admin","admin","admin"));
	}};

	public static User create(User user) {
		user.setId(currentId++);
		users.add(user);
		return user;
	}

	public static User login(String email, String password) {
		for (User u: users) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password))
				return u;
		}
		return null;
	}
}
