package com.quiztionario.model;

import com.quizwork.Answer;
import com.quizwork.Question;
import com.quizwork.ValidationException;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjectiveQuestion extends Question implements Serializable {
	private ArrayList<Option> options;

	public ObjectiveQuestion() {}

	public ObjectiveQuestion(String text) {
		this.text = text;
		this.options = new ArrayList<>();
	}

	public ObjectiveQuestion(long id, String text, Answer correct) {
		this.id = id;
		this.text = text;
		this.correct = correct;
		this.options = new ArrayList<>();
	}

	@Override
	public void validate() throws ValidationException {
		if (options.size() < 2)
			throw new ValidationException("At least two Options are required for each Question");
		for (Option op : options) {
			if (op.getText() == null || op.getText().trim().isEmpty())
				throw new ValidationException("Option Text is required");
		}
		if (correct == null)
			throw new ValidationException("You must select the Correct option for each Question");
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}
}
