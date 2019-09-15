package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.quiztionario.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.quiztionario.model.Question;
import com.quiztionario.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionQuizActivity extends AppCompatActivity {
    private Quiz quiz;
    private ArrayList<String> questionText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_quiz);

        quiz = (Quiz) getIntent().getSerializableExtra("Quiz");

        final ArrayList<String> questionText= new ArrayList<String>();
        if(quiz.getQuestions()!=null) {
            Toast.makeText(getApplicationContext(), "Quiz recebido", Toast.LENGTH_LONG).show();
            List<Question> b = quiz.getQuestions();
            for (Question a : b) {
                questionText.add(a.getText());
            }
            listView = findViewById(R.id.listQuestion);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, questionText);

            listView.setAdapter(adapter);
        }
    }

    public void newQuestion(View view) {
        showCreateQuestion();
    }
    public void showCreateQuestion()
    {
        startActivity(new Intent(this,CreateQuestionActivity.class).putExtra("Quiz",quiz));
    }

}
