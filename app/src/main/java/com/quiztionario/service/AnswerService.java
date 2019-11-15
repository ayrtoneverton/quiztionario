package com.quiztionario.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.quiztionario.dao.AnswerDAO;
import com.quiztionario.model.ObjectiveQuestionCalculator;
import com.quiztionario.model.QuizAnswer;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.SummationQuizCalculator;
import com.quiztionario.model.User;
import com.quiztionario.model.ValidationException;
import com.quiztionario.model.WithContext;

import java.util.List;

public class AnswerService extends WithContext {
	@SuppressLint("StaticFieldLeak")
	private static AnswerService service;

	private AnswerService(Context context) {
		super(context);
	}

	public QuizAnswer create(QuizAnswer quizAnswer) throws ValidationException {
		if (quizAnswer.getQuiz() == null)
			throw new ValidationException("Quiz is required in QuizAnswer");
		if (quizAnswer.getCreator() == null)
			throw new ValidationException("Creator User is required in QuizAnswer");
		if (quizAnswer.getQuestionAnswers().get(0).getQuizAnswer() == null)
			throw new ValidationException("QuizAnswer is required in QuestionAnswer");
		if (quizAnswer.getQuestionAnswers().get(0).getQuestion() == null)
			throw new ValidationException("Question is required in QuestionAnswer");

		quizAnswer.calculateScore(new SummationQuizCalculator(), new ObjectiveQuestionCalculator());

		return AnswerDAO.getInstance(context).create(quizAnswer);
	}

	public long countByUser(User user) throws ValidationException {
		if (user == null)
			throw new ValidationException("Invalid user");

		return AnswerDAO.getInstance(context).countByUser(user);
	}

	public long countByQuiz(Quiz quiz) throws ValidationException {
		if (quiz == null)
			throw new ValidationException("Invalid quiz");

		return AnswerDAO.getInstance(context).countByQuiz(quiz);
	}

	public List<QuizAnswer> findByQuiz(Quiz quiz) throws ValidationException {
		if (quiz == null)
			throw new ValidationException("Invalid quiz");

		return AnswerDAO.getInstance(context).findByQuiz(quiz);
	}

	public static AnswerService getInstance(Context context) {
		if (service == null)
			service = new AnswerService(context);
		return service;
	}
}
