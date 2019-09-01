package com.quiztionario.model;

import java.util.List;

public class Answer
{
    private int id;
    private Quiz quiz;
    private User creator;
    private List<AnswerQuestion> answers;

    public Answer() {}

    public Answer(int id, Quiz quiz, User creator)
    {
        this.id= id;
        this.quiz = quiz;
        this.creator = creator;
    }

    public Answer(int id, Quiz quiz, User creator, List<AnswerQuestion> answers)
    {
        this.id = id;
        this.quiz = quiz;
        this.creator = creator;
        this.answers= answers;
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
    public User getCreator()
    {
        return creator;
    }
    public void setCreator(User creator)
    {
        this.creator = creator;
    }
    public List<AnswerQuestion> getAnswers()
    {
        return answers;
    }
    public void setAnswers(List<AnswerQuestion> answers)
    {
        this.answers = answers;
    }


}