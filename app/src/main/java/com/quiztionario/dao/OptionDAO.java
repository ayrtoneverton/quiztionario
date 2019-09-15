package com.quiztionario.dao;

import java.util.ArrayList;
import java.util.List;
import com.quiztionario.model.Option;

public class OptionDAO {
    private static OptionDAO dao = new OptionDAO();
    private int currentId = 0;
    private List<Option> options = new ArrayList<Option>(){{
        add(new Option());
    }};

    private OptionDAO() {}

    public Option create(Option option) {
        option.setId(++currentId);
        options.add(option);
        return option;
    }

    public static OptionDAO getInstance() {
        return dao;
    }
}
