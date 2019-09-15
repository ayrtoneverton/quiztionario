package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.quiztionario.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.quiztionario.model.Quiz;

public class AddQuestionQuizActivity extends AppCompatActivity {
    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_quiz);

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
