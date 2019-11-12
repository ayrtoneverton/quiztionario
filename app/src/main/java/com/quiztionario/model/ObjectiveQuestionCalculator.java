package com.quiztionario.model;

public class ObjectiveQuestionCalculator implements QuestionCalculator {
	@Override
	public void calculate(QuestionAnswer questionAnswer) throws ValidationException {
		if (questionAnswer.getAnswer() == null)
			throw new ValidationException("Question "
					+ (questionAnswer.getQuizAnswer().getQuestionAnswers().indexOf(questionAnswer)+ 1)
					+ " needs to be answered.");

		if (questionAnswer.getAnswer().getId() == questionAnswer.getQuestion().getCorrect().getId())
			questionAnswer.setScore(1);
		else
			questionAnswer.setScore(0);
	}
}
