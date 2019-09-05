package com.quiztionario.service;

import com.quiztionario.dao.QuizDAO;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;

public class QuizService {
	private static QuizService service = new QuizService();

	private QuizService() {}

	public Quiz create(Quiz quiz) throws ValidationException {
		if (quiz.getName() == null || quiz.getName().trim().isEmpty())
			throw new ValidationException("Quiz Name is required");
		if (quiz.getStart() == null)
			throw new ValidationException("Start Date is required");
		if (quiz.getEnd() == null)
			throw new ValidationException("End Date is required");
		if (quiz.getCategory().getId() == 0) {
			CategoryService.getInstance().create(quiz.getCategory());
		}

		return QuizDAO.getInstance().create(quiz);
	}

	public static QuizService getInstance() {
		return service;
	}
}
