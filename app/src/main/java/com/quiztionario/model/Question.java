package com.quiztionario.model;

import java.util.List;

public class Question
{
    private int id;
    private String text;
    private Quiz quiz;
    private List<Option> options;
    private Option correct;

    public Question() {}

    public Question(int id, String text, Quiz quiz)
    {
        this.id = id;
        this.text = text;
        this.quiz = quiz;
    }

    public Question(int id, String text, Quiz quiz, List<Option> options, Option correct)
    {
        this.id = id;
        this.text = text;
        this.quiz = quiz;
        this.options = options;
        this.correct = correct;
    }

    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public Quiz getQuiz()
    {
        return quiz;
    }
    public void setQuiz(Quiz quiz)
    {
        this.quiz = quiz;
    }
    public List<Option> getOptions()
    {
        return options;
    }
    public void setOptions(List<Option> options)
    {
        this.options = options;
    }
    public Option getCorrect()
    {
        return correct;
    }
    public void setCorrect(Option correct)
    {
        this.correct = correct;
    }
}