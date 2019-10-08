package com.quiztionario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.quiztionario.model.Answer;
import com.quiztionario.model.AnswerQuestion;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.quiztionario.dao.DAO.*;

public class AnswerDAO extends WithDAO {
	private static AnswerDAO answerDAO;

	private AnswerDAO(Context context) {
		super(context);
	}

	public Answer create(Answer answer) {
		SQLiteDatabase db = dao.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(ANSWER_QUIZ, answer.getQuiz().getId());
		values.put(ANSWER_CREATOR, answer.getCreator().getId());
		answer.setId(db.insert(ANSWER_TABLE, null, values));

		for (AnswerQuestion aq : answer.getAnswers()) {
			values.clear();
			values.put(ANSWER_QUESTION_ANSWER, answer.getId());
			values.put(ANSWER_QUESTION_QUESTION, aq.getQuestion().getId());
			values.put(ANSWER_QUESTION_OPTION, aq.getOption().getId());
			aq.setId(db.insert(ANSWER_QUESTION_TABLE, null, values));
		}
		return answer;
	}

	public long countByUser(User user) {
		return DatabaseUtils.queryNumEntries(dao.getReadableDatabase(), ANSWER_TABLE, ANSWER_CREATOR + "=" + user.getId());
	}

	public long countByQuiz(Quiz quiz) {
		return DatabaseUtils.queryNumEntries(dao.getReadableDatabase(), ANSWER_TABLE, ANSWER_QUIZ + "=" + quiz.getId());
	}

	public List<User> findUsersWinnersByQuiz(Quiz quiz) {
		SQLiteDatabase db = dao.getReadableDatabase();
		String sql = "SELECT DISTINCT " + USER_TABLE + ".* FROM "
				+ ANSWER_TABLE + "," + USER_TABLE + " WHERE "
				+ ANSWER_CREATOR + "=" + USER_ID + " AND " + ANSWER_QUIZ + "= ?"
				+ " AND NOT EXISTS (SELECT * FROM " + ANSWER_QUESTION_TABLE + "," + QUESTION_TABLE + " WHERE "
				+ ANSWER_QUESTION_QUESTION + "=" + QUESTION_ID + " AND " + ANSWER_ID + "=" + ANSWER_QUESTION_ANSWER
				+ " AND " + ANSWER_QUESTION_OPTION + "!=" + QUESTION_OPTION + ")";
		Cursor c = db.rawQuery(sql, new String[]{String.valueOf(quiz.getId())});

		List<User> result = new ArrayList<>();
		while (c.moveToNext()) {
			result.add(new User(
					c.getLong(c.getColumnIndex(USER_ID)),
					c.getString(c.getColumnIndex(USER_NAME)),
					c.getString(c.getColumnIndex(USER_EMAIL))));
		}
		c.close();
		return result;
	}

	public static AnswerDAO getInstance(Context context) {
		if (answerDAO == null)
			answerDAO = new AnswerDAO(context);
		return answerDAO;
	}
}
