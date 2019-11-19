package com.quizwork;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Quiz implements Serializable {
	private long id;
	private String name;
	private boolean open;
	private Integer code;
	private GregorianCalendar start;
	private GregorianCalendar end;
	private Category category;
	private User creator;
	private List<QuizAnswer> quizAnswers;
	private List<Question> questions;

	public Quiz() {
	}

	public Quiz(long id, String name, boolean open, Integer code, GregorianCalendar start, GregorianCalendar end, Category category, User creator) {
		this.id = id;
		this.name = name;
		this.open = open;
		this.code = code;
		this.start = start;
		this.end = end;
		this.category = category;
		this.creator = creator;
		this.questions = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public GregorianCalendar getStart() {
		return start;
	}

	public void setStart(GregorianCalendar start) {
		this.start = start;
	}

	public GregorianCalendar getEnd() {
		return end;
	}

	public void setEnd(GregorianCalendar end) {
		this.end = end;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<QuizAnswer> getQuizAnswers() {
		return quizAnswers;
	}

	public void setQuizAnswers(List<QuizAnswer> quizAnswers) {
		this.quizAnswers = quizAnswers;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@NonNull
	@Override
	public String toString() {
		return name;
	}
}
