package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.quiztionario.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.quiztionario.dao.QuizDAO;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.QuizService;

import java.util.ArrayList;

public class AddQuestionQuizActivity extends AppCompatActivity {
    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_quiz);

        Bundle bundle = getIntent().getExtras();
        quiz = (Quiz) getIntent().getSerializableExtra("Quiz");
    }

    public void newQuestion(View view) {
        showCreateQuestion();
    }
    public void showCreateQuestion()
    {
        startActivity(new Intent(this,CreateQuestionActivity.class).putExtra("Quiz",quiz));
    }

}
