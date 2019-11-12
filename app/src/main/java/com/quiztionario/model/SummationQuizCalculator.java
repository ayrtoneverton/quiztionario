package com.quiztionario.model;

public class SummationQuizCalculator implements QuizCalculator {
	@Override
	public void calculate(QuizAnswer quizAnswer) throws ValidationException {
		int score = 0;
		for (QuestionAnswer questionAnswer: quizAnswer.getQuestionAnswers())
			score += questionAnswer.getScore();
		quizAnswer.setScore(score);
	}
}
