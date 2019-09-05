package com.quiztionario.dao;

import com.quiztionario.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
	private static UserDAO dao = new UserDAO();
	private int currentId = 0;
	private List<User> users = new ArrayList<User>(){{
		add(new User("Admin","admin","admin"));
	}};

	private UserDAO() {}

	public User create(User user) {
		user.setId(currentId++);
		users.add(user);
		return user;
	}

	public User login(String email, String password) {
		for (User u: users) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password))
				return u;
		}
		return null;
	}
	public static UserDAO getInstance() {
		return dao;
	}
}
