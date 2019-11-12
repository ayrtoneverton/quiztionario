package com.quiztionario.model;

public interface QuizCalculator {
	void calculate(QuizAnswer quizAnswer) throws ValidationException;
}
