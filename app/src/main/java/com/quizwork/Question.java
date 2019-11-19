package com.quizwork;

import java.io.Serializable;

public abstract class Question implements Serializable {
	protected long id;
	protected String text;
	protected Quiz quiz;
	protected Answer correct;

	public abstract void  validate() throws ValidationException;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Answer getCorrect() {
		return correct;
	}

	public void setCorrect(Answer correct) {
		this.correct = correct;
	}
}