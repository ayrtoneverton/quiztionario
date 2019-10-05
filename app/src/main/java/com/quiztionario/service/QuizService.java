package com.quiztionario.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.quiztionario.dao.QuizDAO;
import com.quiztionario.model.Question;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;
import com.quiztionario.model.WithContext;

import java.util.ArrayList;

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
			if (quiz.getQuestions().size() == 0)
				throw new ValidationException("At least one Question required");
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

	public Quiz searchCode(String code) throws ValidationException {
		if(code.trim().isEmpty()){
			throw new ValidationException("Quiz code is required");
		}else if(QuizDAO.getInstance(context).findbyCode(Integer.parseInt(code))==null){
			throw new ValidationException("There is no valid Quiz with this code!");
		}else{
			return QuizDAO.getInstance(context).findbyCode(Integer.parseInt(code));
		}
	}

	public ArrayList<Quiz> searchText(String text) throws ValidationException {
		if(text.trim().isEmpty()){
			throw new ValidationException("You need a Text to search.");
		}else if(QuizDAO.getInstance(context).findAllByText(text)==null){
			throw new ValidationException("There is no valid Quiz with this text!");
		}else{
			return QuizDAO.getInstance(context).findAllByText(text);
		}
	}
}
