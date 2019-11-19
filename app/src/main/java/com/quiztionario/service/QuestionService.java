package com.quiztionario.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.quiztionario.dao.QuestionDAO;
import com.quizwork.Question;
import com.quizwork.ValidationException;
import com.quiztionario.model.WithContext;

public class QuestionService extends WithContext {
	@SuppressLint("StaticFieldLeak")
	private static QuestionService service;

	private QuestionService(Context context) {
		super(context);
	}

	public Question create(Question question) throws ValidationException {
		if (question.getText() == null || question.getText().trim().isEmpty())
			throw new ValidationException("Question Text is required");
		if (question.getQuiz() == null)
			throw new ValidationException("Question Quiz is required");

		question.validate();

		return QuestionDAO.getInstance(context).create(question);
	}

	public static QuestionService getInstance(Context context) {
		if (service == null)
			service = new QuestionService(context);
		return service;
	}
}



