package com.quiztionario.model;

public class AnswerQuestion {
	private long id;
	private Answer answer;
	private Question question;
	private Option option;

	public AnswerQuestion(Answer answer, Question question) {
		this.answer = answer;
		this.question = question;
	}

	public long getId() {
		return id;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
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

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}
}