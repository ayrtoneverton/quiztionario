package com.quiztionario.dao;

import com.quiztionario.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizDAO {
	private static QuizDAO dao = new QuizDAO();
	private int currentId = 0;
	private List<Quiz> quizzes = new ArrayList<>();

	private QuizDAO() {}

	public Quiz create(Quiz quiz) {
		quiz.setId(++currentId);
		quizzes.add(quiz);
		return quiz;
	}

	public static QuizDAO getInstance() {
		return dao;
	}
}
