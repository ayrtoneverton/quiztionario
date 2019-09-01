package com.quiztionario.model;

public class Option
{
    private int id;
    private String text;
    private Question question;

    public Option() {}

    public Option(int id, String text)
    {
        this.id = id;
        this.text = text;
    }
    public Option(int id, String text, Question question)
    {
        this.id = id;
        this.text = text;
        this.question = question;
    }

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
    public void setText(String text)
    {
        this.text = text;
    }
    public Question getQuestion()
    {
        return question;
    }
    public void setQuestion(Question question)
    {
        this.question = question;
    }
}
