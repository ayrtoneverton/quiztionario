package com.quizwork;

public class QuestionAnswer {
	private long id;
	private QuizAnswer quizAnswer;
	private Question question;
	private Answer answer;
	private Integer score;

	public QuestionAnswer(QuizAnswer quizAnswer, Question question) {
		this.quizAnswer = quizAnswer;
		this.question = question;
	}

	public long getId() {
		return id;
	}

	public QuizAnswer getQuizAnswer() {
		return quizAnswer;
	}

	public void setQuizAnswer(QuizAnswer quizAnswer) {
		this.quizAnswer = quizAnswer;
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

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
