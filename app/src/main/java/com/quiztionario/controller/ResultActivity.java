package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.quiztionario.R;
import com.quiztionario.model.User;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.QuizService;

public class ResultActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        user = (User) getIntent().getSerializableExtra("user");


        TextView attempted = findViewById(R.id.result_attempt);

        try {
            attempted.setText(String.valueOf(QuizService.getInstance(this).attemptedQuiz(user)));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

}
