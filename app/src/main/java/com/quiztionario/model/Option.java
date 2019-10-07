package com.quiztionario.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Option implements Serializable {
    private long id;
    private String text;
    private Question question;

    public Option(String text, Question question) {
        this.text = text;
        this.question = question;
    }

    public Option(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }

    @NonNull
    @Override
    public String toString() {
        return text;
    }
}
