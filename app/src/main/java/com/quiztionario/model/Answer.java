package com.quiztionario.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Answer implements Serializable {
	private long id;
	private Quiz quiz;
	private User creator;
	private List<AnswerQuestion> answers;

	public Answer(Quiz quiz, User creator) {
		this.quiz = quiz;
		this.creator = creator;
		this.answers = new ArrayList<>();
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

	public List<AnswerQuestion> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerQuestion> answers) {
		this.answers = answers;
	}


}