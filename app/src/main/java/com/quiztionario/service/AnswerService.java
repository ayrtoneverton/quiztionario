package com.quiztionario.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.quiztionario.dao.AnswerDAO;
import com.quiztionario.model.Answer;
import com.quiztionario.model.AnswerQuestion;
import com.quiztionario.model.ValidationException;
import com.quiztionario.model.WithContext;

public class AnswerService extends WithContext {
	@SuppressLint("StaticFieldLeak")
	private static AnswerService service;

	private AnswerService(Context context) {
		super(context);
	}

	public Answer create(Answer answer) throws ValidationException {
		if (answer.getQuiz() == null)
			throw new ValidationException("Quiz is required in Answer");
		if (answer.getCreator() == null)
			throw new ValidationException("Creator User is required in Answer");
		if (answer.getAnswers().get(0).getAnswer() == null)
			throw new ValidationException("Answer is required in AnswerQuestion");
		if (answer.getAnswers().get(0).getQuestion() == null)
			throw new ValidationException("Question is required in AnswerQuestion");
		for (AnswerQuestion aq : answer.getAnswers()) {
			if (aq.getOption() == null)
				throw new ValidationException("Question "+( answer.getAnswers().indexOf(aq) + 1 )+" needs to be answered.");
		}

		return AnswerDAO.getInstance(context).create(answer);
	}

	public static AnswerService getInstance(Context context) {
		if (service == null)
			service = new AnswerService(context);
		return service;
	}
}
