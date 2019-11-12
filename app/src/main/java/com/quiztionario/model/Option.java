package com.quiztionario.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Option extends Answer implements Serializable {
	private String text;

	public Option(String text, Question question) {
		this.text = text;
		this.question = question;
	}

	public Option(long id, String text) {
		this.id = id;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@NonNull
	@Override
	public String toString() {
		return text;
	}
}
