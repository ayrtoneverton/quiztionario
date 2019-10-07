package com.quiztionario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.quiztionario.model.Answer;
import com.quiztionario.model.AnswerQuestion;
import com.quiztionario.model.User;

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

		for (AnswerQuestion aq: answer.getAnswers()) {
			values.clear();
			values.put(ANSWER_QUESTION_ANSWER, answer.getId());
			values.put(ANSWER_QUESTION_QUESTION, aq.getQuestion().getId());
			values.put(ANSWER_QUESTION_OPTION, aq.getOption().getId());
			aq.setId(db.insert(ANSWER_QUESTION_TABLE, null, values));
		}
		return answer;
	}

	public long countByUser(User user) {
		SQLiteDatabase db = dao.getReadableDatabase();
		return DatabaseUtils.queryNumEntries(db, ANSWER_TABLE, ANSWER_CREATOR +"="+ user.getId());
	}

	public static AnswerDAO getInstance(Context context) {
		if (answerDAO == null)
			answerDAO = new AnswerDAO(context);
		return answerDAO;
	}
}
