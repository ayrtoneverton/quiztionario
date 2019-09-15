package com.quiztionario.service;

import com.quiztionario.model.Option;
import com.quiztionario.model.ValidationException;
import com.quiztionario.dao.OptionDAO;

public class OptionService
{
    private static OptionService service = new OptionService();

    private OptionService() {}

    public Option create(Option option) throws ValidationException {
        if (option.getText() == null || option.getText().trim().isEmpty())
            throw new ValidationException("Text is required");
        if (option.getQuestion() == null)
            throw new ValidationException("Question is required");

        return OptionDAO.getInstance().create(option);
    }

    public static OptionService getInstance() {
        return service;
    }
}



