package com.quiztionario.model;

import com.quizwork.QuestionAnswer;
import com.quizwork.QuizAnswer;
import com.quizwork.QuizCalculator;

public class SummationQuizCalculator implements QuizCalculator {
	@Override
	public void calculate(QuizAnswer quizAnswer) {
		int score = 0;
		for (QuestionAnswer questionAnswer: quizAnswer.getQuestionAnswers())
			score += questionAnswer.getScore();
		quizAnswer.setScore(score);
	}
}
