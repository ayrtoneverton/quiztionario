package com.quiztionario.dao;

import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

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

	public ArrayList<Quiz> findQuizByUser(User user){
		ArrayList<Quiz> userQuiz = new ArrayList<>();
		for(Quiz quiz: quizzes){
			if (quiz.getCreator().equals(user)){
				userQuiz.add(quiz);
			}
		}
		return userQuiz;
	}

	public static QuizDAO getInstance() {
		return dao;
	}
}
