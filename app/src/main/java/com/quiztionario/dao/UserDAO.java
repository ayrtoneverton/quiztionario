package com.quiztionario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quiztionario.model.User;

import static com.quiztionario.dao.DAO.*;

public class UserDAO extends WithDAO {
	private static UserDAO userDAO;

	private UserDAO(Context context) {
		super(context);
	}

	public User create(User user) {
		SQLiteDatabase db = dao.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(USER_NAME, user.getName());
		values.put(USER_EMAIL, user.getEmail());
		values.put(USER_PASSWORD, user.getPassword());

		user.setId(db.insert(USER_TABLE, null, values));
		return user;
	}

	public User login(String email, String password) {
		SQLiteDatabase db = dao.getReadableDatabase();
		Cursor c = db.query(USER_TABLE, null,
				USER_EMAIL + " = ? and " + USER_PASSWORD + " = ?",
				new String[]{email, password},
				null, null, null);

		User user = null;
		if (c.moveToFirst()) {
			user = new User(
					c.getLong(c.getColumnIndex(USER_ID)),
					c.getString(c.getColumnIndex(USER_NAME)),
					c.getString(c.getColumnIndex(USER_EMAIL)),
					c.getString(c.getColumnIndex(USER_PASSWORD))
			);
		}
		c.close();
		return user;
	}

	public static UserDAO getInstance(Context context) {
		if (userDAO == null)
			userDAO = new UserDAO(context);
		return userDAO;
	}
}
