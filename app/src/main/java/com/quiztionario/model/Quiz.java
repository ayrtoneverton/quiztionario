package com.quiztionario.model;

import java.util.Date;
import java.util.List;

public class Quiz
{
    private int id;
    private String name;
    private Date start;
    private Date end;
    private Category category;
    private User creator;
    private List<Answer> answers;
    private List<Question> questions;

    public Quiz() {}

    public Quiz(int id, String name, Date start, Date end, Category category, User creator)
    {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.category = category;
        this.creator = creator;
    }

    public Quiz(int id, String name, Date start, Date end, Category category, User creator, List<Answer> answers, List<Question> questions)
    {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.category = category;
        this.creator = creator;
        this.answers = answers;
        this.questions = questions;
    }

    public Quiz(int id, String name, Date start, Date end)
    {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

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
    public Date getStart()
    {
        return start;
    }
    public void setStart(Date start)
    {
        this.start = start;
    }
    public Date getEnd()
    {
        return end;
    }
    public void setEnd(Date end)
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
