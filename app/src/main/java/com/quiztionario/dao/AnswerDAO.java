package com.quiztionario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.quiztionario.model.QuestionAnswer;
import com.quiztionario.model.QuizAnswer;
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

	public QuizAnswer create(QuizAnswer quizAnswer) {
		SQLiteDatabase db = dao.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(QUIZ_ANSWER, quizAnswer.getQuiz().getId());
		values.put(QUIZ_ANSWER_CREATOR, quizAnswer.getCreator().getId());
		values.put(QUIZ_ANSWER_SCORE, quizAnswer.getScore());
		quizAnswer.setId(db.insert(QUIZ_ANSWER_TABLE, null, values));

		for (QuestionAnswer aq : quizAnswer.getQuestionAnswers()) {
			values.clear();
			values.put(QUESTION_ANSWER_ANSWER, quizAnswer.getId());
			values.put(QUESTION_ANSWER_QUESTION, aq.getQuestion().getId());
			values.put(QUESTION_ANSWER_OPTION, aq.getAnswer().getId());
			values.put(QUESTION_ANSWER_SCORE, aq.getScore());
			aq.setId(db.insert(QUESTION_ANSWER_TABLE, null, values));
		}
		return quizAnswer;
	}

	public long countByUser(User user) {
		return DatabaseUtils.queryNumEntries(dao.getReadableDatabase(), QUIZ_ANSWER_TABLE, QUIZ_ANSWER_CREATOR + "=" + user.getId());
	}

	public long countByQuiz(Quiz quiz) {
		return DatabaseUtils.queryNumEntries(dao.getReadableDatabase(), QUIZ_ANSWER_TABLE, QUIZ_ANSWER + "=" + quiz.getId());
	}

	public List<User> findUsersWinnersByQuiz(Quiz quiz) {
		SQLiteDatabase db = dao.getReadableDatabase();
		String sql = "SELECT DISTINCT " + USER_TABLE + ".* FROM "
				+ QUIZ_ANSWER_TABLE + "," + USER_TABLE + " WHERE "
				+ QUIZ_ANSWER_CREATOR + "=" + USER_ID + " AND " + QUIZ_ANSWER + "= ?"
				+ " AND NOT EXISTS (SELECT * FROM " + QUESTION_ANSWER_TABLE + "," + QUESTION_TABLE + " WHERE "
				+ QUESTION_ANSWER_QUESTION + "=" + QUESTION_ID + " AND " + QUIZ_ANSWER_ID + "=" + QUESTION_ANSWER_ANSWER
				+ " AND " + QUESTION_ANSWER_OPTION + "!=" + QUESTION_OPTION + ")";
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
