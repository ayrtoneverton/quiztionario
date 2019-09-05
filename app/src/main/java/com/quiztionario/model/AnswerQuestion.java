package com.quiztionario.model;

public class AnswerQuestion
{
    private int id;
    private Question question;
    private Option option;

    public AnswerQuestion() {}

    public AnswerQuestion(int id, Question question, Option option)
    {
        this.id = id;
        this.question = question;
        this.option = option;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public Question getQuestion()
    {
        return question;
    }
    public void setQuestion(Question question)
    {
        this.question = question;
    }
    public Option getOption()
    {
        return option;
    }
    public void setOption(Option option)
    {
        this.option = option;
    }

}