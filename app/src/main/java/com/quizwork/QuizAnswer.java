package com.quizwork;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizAnswer implements Serializable {
	private long id;
	private Quiz quiz;
	private User creator;
	private List<QuestionAnswer> questionAnswers;
	private Integer score;

	public QuizAnswer(Quiz quiz, User creator) {
		this.quiz = quiz;
		this.creator = creator;
		this.questionAnswers = new ArrayList<>();
	}

	public QuizAnswer(long id, User creator, Integer score) {
		this.id = id;
		this.creator = creator;
		this.score = score;
		this.questionAnswers = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<QuestionAnswer> getQuestionAnswers() {
		return questionAnswers;
	}

	public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void calculateScore(QuizCalculator quizCalculator, QuestionCalculator questionCalculator) throws ValidationException {
		for (QuestionAnswer questionAnswer: questionAnswers) {
			questionCalculator.calculate(questionAnswer);
		}
		quizCalculator.calculate(this);
	}

	@Override
	public String toString() {
		return creator.getName() + " (" + score + ")";
	}
}
