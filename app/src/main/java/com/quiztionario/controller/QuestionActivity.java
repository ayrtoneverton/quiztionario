package com.quiztionario.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.quiztionario.R;
import com.quiztionario.model.Answer;
import com.quiztionario.model.Option;
import com.quiztionario.model.Question;
import com.quiztionario.model.Quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private Quiz quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        //quiz = (Quiz) getIntent().getSerializableExtra("quiz");

        Question question1 = new Question();
        question1.setText("Quem descobriu o Brasil?");
        Option option1 = new Option();
        option1.setText("Pedro Alvares Cabral");
        Option option2 = new Option();
        option2.setText("Pedro");
        Option option3 = new Option();
        option3.setText("Chico");
        ArrayList<Option> listOption1 = new ArrayList<>();
        listOption1.add(option1);
        listOption1.add(option2);
        listOption1.add(option3);
        question1.setOptions(listOption1);


        Question question2 = new Question();
        question2.setText("Quem descobriu a América?");
        Option option21 = new Option();
        option21.setText("Cristovão Colombo");
        Option option22 = new Option();
        option22.setText("Marcos");
        Option option23 = new Option();
        option23.setText("Cézar");
        ArrayList<Option> listOption21 = new ArrayList<>();
        listOption21.add(option21);
        listOption21.add(option22);
        listOption21.add(option23);
        question2.setOptions(listOption21);


        final List<Question> listQ = new ArrayList<>();
        listQ.add(question1);
        listQ.add(question2);
        List<String> listQuestions = new ArrayList<>();

        for (Question i : listQ){
            listQuestions.add(i.getText());
        }

        ListView listView = (ListView) findViewById(R.id.listQuestions);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listQuestions);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                List<String> answers;
                answers = new ArrayList<>();

                for (Option j : listQ.get(i).getOptions()){
                    answers.add(j.getText());
                }
                Intent intent = new Intent(view.getContext(), AnswersActivity.class);
                intent.putExtra("question", listQ.get(i).getText());
                intent.putExtra("answers", (Serializable) answers);
                startActivity(intent);

            }
        });


    }


}
