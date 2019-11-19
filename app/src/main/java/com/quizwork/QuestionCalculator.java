package com.quizwork;

public interface QuestionCalculator {
	void calculate(QuestionAnswer questionAnswer) throws ValidationException;
}
