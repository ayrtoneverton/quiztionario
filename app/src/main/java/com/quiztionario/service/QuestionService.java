package com.quiztionario.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.quiztionario.dao.QuestionDAO;
import com.quiztionario.model.Option;
import com.quiztionario.model.Question;
import com.quiztionario.model.ValidationException;
import com.quiztionario.model.WithContext;

public class QuestionService extends WithContext {
    @SuppressLint("StaticFieldLeak")
    private static QuestionService service;

    private QuestionService(Context context) {
        super(context);
    }

    public Question create(Question question) throws ValidationException {
        if (question.getText() == null || question.getText().trim().isEmpty())
            throw new ValidationException("Question Text is required");
        if (question.getQuiz() == null)
            throw new ValidationException("Question Quiz is required");
        if (question.getOptions().size() < 2)
            throw new ValidationException("At least two Options are required for each Question");
        for (Option op : question.getOptions()) {
            if (op.getText() == null || op.getText().trim().isEmpty())
                throw new ValidationException("Option Text is required");
        }
        if (question.getCorrect() == null)
            throw new ValidationException("You must select the Correct option for each Question");

        return QuestionDAO.getInstance(context).create(question);
    }

    public static QuestionService getInstance(Context context) {
        if(service == null)
            service = new QuestionService(context);
        return service;
    }
}



