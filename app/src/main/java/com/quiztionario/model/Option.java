package com.quiztionario.model;

import android.text.Editable;

import java.io.Serializable;

public class Option implements Serializable
{
    private int id;
    private String text;
    private Question question;

    public Option() {}


    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getText()
    {
        return text;
    }
    public void setText(String text) { this.text = text; }
    public Question getQuestion()
    {
        return question;
    }
    public void setQuestion(Question question)
    {
        this.question = question;
    }
}
