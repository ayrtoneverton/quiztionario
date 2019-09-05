package com.quiztionario.service;

import com.quiztionario.dao.UserDAO;
import com.quiztionario.model.User;
import com.quiztionario.model.ValidationException;

public class UserService {
	private static UserService service = new UserService();

	private UserService() {}

	public User create(User user) throws ValidationException {
		if (user.getName() == null || user.getName().trim().isEmpty())
			throw new ValidationException("Name is required");
		if (user.getEmail() == null || user.getEmail().trim().isEmpty())
			throw new ValidationException("E-mail is required");
		if (user.getPassword() == null || user.getPassword().trim().isEmpty())
			throw new ValidationException("Password is required");

		return UserDAO.getInstance().create(user);
	}

	public User login(String email, String password) throws ValidationException {
		if (email == null || email.trim().isEmpty())
			throw new ValidationException("E-mail is required");
		if (password == null || password.trim().isEmpty())
			throw new ValidationException("Password is required");

		User user = UserDAO.getInstance().login(email, password);

		if (user == null)
			throw new ValidationException("Invalid E-mail or Password");
		return user;
	}

	public static UserService getInstance() {
		return service;
	}
}
