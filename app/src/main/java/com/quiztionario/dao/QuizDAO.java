package com.quiztionario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quiztionario.model.Category;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.quiztionario.dao.DAO.*;

public class QuizDAO extends WithDAO {
	private static QuizDAO quizDAO;

	private QuizDAO(Context context) {
		super(context);
	}

	public Quiz create(Quiz quiz) {
		SQLiteDatabase db = dao.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(QUIZ_NAME, quiz.getName());
		values.put(QUIZ_OPEN, quiz.isOpen());
		values.put(QUIZ_CODE, quiz.getCode());
		values.put(QUIZ_START, getDate(quiz.getStart()));
		values.put(QUIZ_END, getDate(quiz.getEnd()));
		values.put(QUIZ_CATEGORY, quiz.getCategory().getId());
		values.put(QUIZ_USER, quiz.getCreator().getId());

		quiz.setId(db.insert(QUIZ_TABLE, null, values));
		return quiz;
	}

	public List<Quiz> findQuizByUser(User user) {
		SQLiteDatabase db = dao.getReadableDatabase();
		Cursor c = db.query(QUIZ_TABLE, null,
				QUIZ_USER + " = ?",
				new String[]{String.valueOf(user.getId())},
				null, null, null);

		List<Quiz> result = new ArrayList<>();
		while (c.moveToNext()) {
			result.add(new Quiz(
					c.getLong(c.getColumnIndex(QUIZ_ID)),
					c.getString(c.getColumnIndex(QUIZ_NAME)),
					c.getInt(c.getColumnIndex(QUIZ_OPEN)) == 1,
					c.getInt(c.getColumnIndex(QUIZ_CODE)),
					getDate(c.getString(c.getColumnIndex(QUIZ_START))),
					getDate(c.getString(c.getColumnIndex(QUIZ_END))),
					new Category(c.getLong(c.getColumnIndex(QUIZ_CATEGORY))),
					new User(c.getLong(c.getColumnIndex(QUIZ_USER)))));
		}
		c.close();
		return result;
	}

	public Quiz findByCode(String code) {
		SQLiteDatabase db = dao.getReadableDatabase();
		Cursor c = db.query(QUIZ_TABLE, null,
				QUIZ_CODE + " = ?",
				new String[]{code},
				null, null, null);

		Quiz result = null;
		while (c.moveToNext()) {
			result = new Quiz(
					c.getLong(c.getColumnIndex(QUIZ_ID)),
					c.getString(c.getColumnIndex(QUIZ_NAME)),
					c.getInt(c.getColumnIndex(QUIZ_OPEN)) == 1,
					c.getInt(c.getColumnIndex(QUIZ_CODE)),
					getDate(c.getString(c.getColumnIndex(QUIZ_START))),
					getDate(c.getString(c.getColumnIndex(QUIZ_END))),
					new Category(c.getLong(c.getColumnIndex(QUIZ_CATEGORY))),
					new User(c.getLong(c.getColumnIndex(QUIZ_USER))));
		}
		c.close();
		return result;
	}

	public ArrayList<Quiz> findAllByText(String text) {
		SQLiteDatabase db = dao.getReadableDatabase();
		Cursor c = db.query(QUIZ_TABLE, null,
				QUIZ_NAME + " like '%?%'",
				new String[]{text},
				null, null, null);

		ArrayList<Quiz> result = new ArrayList<>();
		while (c.moveToNext()) {
			result.add(new Quiz(
					c.getLong(c.getColumnIndex(QUIZ_ID)),
					c.getString(c.getColumnIndex(QUIZ_NAME)),
					c.getInt(c.getColumnIndex(QUIZ_OPEN)) == 1,
					c.getInt(c.getColumnIndex(QUIZ_CODE)),
					getDate(c.getString(c.getColumnIndex(QUIZ_START))),
					getDate(c.getString(c.getColumnIndex(QUIZ_END))),
					new Category(c.getLong(c.getColumnIndex(QUIZ_CATEGORY))),
					new User(c.getLong(c.getColumnIndex(QUIZ_USER)))));
		}
		c.close();
		return result;
	}

	public static QuizDAO getInstance(Context context) {
		if (quizDAO == null)
			quizDAO = new QuizDAO(context);
		return quizDAO;
	}
}
