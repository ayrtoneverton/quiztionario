package com.quiztionario.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.quiztionario.R;
import com.quiztionario.model.Option;
import com.quiztionario.model.Question;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.OptionService;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionActivity extends AppCompatActivity {

    private Question question;
    private Quiz quiz;
    private String correctOption;
    private ArrayList<Option> options;
    private ArrayList<String> optionText;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        quiz = (Quiz) getIntent().getSerializableExtra("Quiz");
        Question question = new Question();

        options = new ArrayList<>();
        final ArrayList<String> optionText= new ArrayList<String>();

        listView = findViewById(R.id.listOption);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,optionText);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                correctOption=optionText.get(position);

            }
        });
    }
    public void addOptionToQuestion(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Option");
        // DECLARACAO DO EDITTEXT
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setNeutralButton("Done", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Option option = new Option();
                option.setQuestion(question);
                option.setText(input.getText().toString());
                adapter.add(option.getText());
                options.add(option);
                try {
                    Option optionb = OptionService.getInstance().create(option);
                    options.add(optionb);
                    adapter.add(option.getText());

                } catch (ValidationException msg) {
                    msg.getMessage();
                }
            }
        });
        builder.show();
        // FORÇA O TECLADO APARECER AO ABRIR O ALERT
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void saveQuestion(){
        EditText editText = findViewById(R.id.QuestionName);
        question.setText(editText.getText().toString());
        Option correct = new Option();
        for(Option a : options) {
            if(a.getText().equals(correctOption)){
                correct = a;
            }
        }
        question.setCorrect(correct);
        question.setOptions(options);
        returnToAddQuestion();
    }
    public void returnToAddQuestion(){
        List<Question> a = quiz.getQuestions();
        a.add(question);
        quiz.setQuestions(a);
        startActivity(new Intent(this, AddQuestionQuizActivity.class).putExtra("Quiz", quiz));
    }
}
