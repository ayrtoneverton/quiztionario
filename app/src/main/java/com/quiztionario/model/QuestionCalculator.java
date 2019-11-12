package com.quiztionario.model;

public interface QuestionCalculator {
	void calculate(QuestionAnswer questionAnswer) throws ValidationException;
}
