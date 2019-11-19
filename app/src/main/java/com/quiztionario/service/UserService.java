package com.quiztionario.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.quiztionario.dao.UserDAO;
import com.quizwork.User;
import com.quizwork.ValidationException;
import com.quiztionario.model.WithContext;

public class UserService extends WithContext {
	@SuppressLint("StaticFieldLeak")
	private static UserService service;

	private UserService(Context context) {
		super(context);
	}

	public User create(User user) throws ValidationException {
		if (user.getName() == null || user.getName().trim().isEmpty())
			throw new ValidationException("Name is required");
		if (user.getEmail() == null || user.getEmail().trim().isEmpty())
			throw new ValidationException("E-mail is required");
		if (user.getPassword() == null || user.getPassword().trim().isEmpty())
			throw new ValidationException("Password is required");

		return UserDAO.getInstance(context).create(user);
	}

	public User login(String email, String password) throws ValidationException {
		if (email == null || email.trim().isEmpty())
			throw new ValidationException("E-mail is required");
		if (password == null || password.trim().isEmpty())
			throw new ValidationException("Password is required");

		User user = UserDAO.getInstance(context).login(email, password);

		if (user == null)
			throw new ValidationException("Invalid E-mail or Password");
		return user;
	}

	public static UserService getInstance(Context context) {
		if (service == null)
			service = new UserService(context);
		return service;
	}
}
