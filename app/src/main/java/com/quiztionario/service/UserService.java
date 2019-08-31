package com.quiztionario.service;

import com.quiztionario.dao.UserDAO;
import com.quiztionario.model.User;
import com.quiztionario.model.ValidationException;

public class UserService {
	public static User create(User user) {
		if (user.getName() == null || user.getName().trim().isEmpty())
			throw new ValidationException("Name is required");
		if (user.getEmail() == null || user.getEmail().trim().isEmpty())
			throw new ValidationException("E-mail is required");
		if (user.getPassword() == null || user.getPassword().trim().isEmpty())
			throw new ValidationException("Password is required");

		return UserDAO.create(user);
	}

	public static User login(String email, String password) {
		if (email == null || email.trim().isEmpty())
			throw new ValidationException("E-mail is required");
		if (password == null || password.trim().isEmpty())
			throw new ValidationException("Password is required");

		User user = UserDAO.login(email, password);

		if (user == null)
			throw new ValidationException("Invalid E-mail or Password");
		return user;
	}
}
