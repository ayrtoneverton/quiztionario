package com.quiztionario.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.quiztionario.dao.QuizDAO;
import com.quiztionario.model.Question;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;
import com.quiztionario.model.WithContext;

public class QuizService extends WithContext {
	@SuppressLint("StaticFieldLeak")
	private static QuizService service;

	private QuizService(Context context) {
		super(context);
	}

	public Quiz create(Quiz quiz) throws ValidationException {
		if (quiz.getQuestions() == null) {
			if (quiz.getName() == null || quiz.getName().trim().isEmpty())
				throw new ValidationException("Quiz Name is required");
			if (quiz.getStart() == null)
				throw new ValidationException("Start Date is required");
			if (quiz.getEnd() == null)
				throw new ValidationException("End Date is required");
			if (quiz.getCategory().getId() == 0)
				quiz.setCategory(CategoryService.getInstance(context).create(quiz.getCategory()));

			quiz.setCode(quiz.isOpen() ? null : (int)(Math.random() * 99999 + 1));
			return null;
		} else {
			QuizDAO.getInstance(context).beginTransaction();
			try {
				QuizDAO.getInstance(context).create(quiz);
				for (Question q: quiz.getQuestions()) {
					q.setQuiz(quiz);
					QuestionService.getInstance(context).create(q);
				}
				QuizDAO.getInstance(context).setTransactionSuccessful();
			} finally {
				QuizDAO.getInstance(context).endTransaction();
			}
			return quiz;
		}
	}

	public static QuizService getInstance(Context context) {
		if(service == null)
			service = new QuizService(context);
		return service;
	}
}
