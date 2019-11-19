package com.quizwork;

import java.io.Serializable;

public abstract class Answer implements Serializable {
	protected long id;
	protected Question question;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}
