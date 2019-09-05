package com.quiztionario.model;

import java.util.GregorianCalendar;
import java.util.List;

public class Quiz {
    private int id;
    private String name;
    private GregorianCalendar start;
    private GregorianCalendar end;
    private Category category;
    private User creator;
    private List<Answer> answers;
    private List<Question> questions;

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public GregorianCalendar getStart()
    {
        return start;
    }
    public void setStart(GregorianCalendar start)
    {
        this.start = start;
    }
    public GregorianCalendar getEnd()
    {
        return end;
    }
    public void setEnd(GregorianCalendar end)
    {
        this.end = end;
    }
    public Category getCategory()
    {
        return category;
    }
    public void setCategory(Category category)
    {
        this.category = category;
    }
    public User getCreator()
    {
        return creator;
    }
    public void setCreator(User creator)
    {
        this.creator = creator;
    }
    public List<Answer> getAnswers()
    {
        return answers;
    }
    public void setAnswers(List<Answer> answers)
    {
        this.answers = answers;
    }
    public List<Question> getQuestions()
    {
        return questions;
    }
    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }
}
