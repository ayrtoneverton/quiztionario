package com.quiztionario.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
	private int id;
	private String text;
	private Quiz quiz;
	private ArrayList<Option> options;
	private Option correct;

	public Question() {}

	public Question(String text) {
		this.text = text;
		this.options = new ArrayList<>();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public Option getCorrect() {
		return correct;
	}

	public void setCorrect(Option correct) {
		this.correct = correct;
	}
}