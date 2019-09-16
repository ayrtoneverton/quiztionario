package com.quiztionario.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.quiztionario.R;

import java.util.List;

public class AnswersActivity extends AppCompatActivity {
    private List<String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        TextView txtQuestion = findViewById(R.id.txtQuestion);
        String question = (String) getIntent().getSerializableExtra("question");
        txtQuestion.setText(question);

        Toast.makeText(this,question,Toast.LENGTH_SHORT).show();

        answers = (List<String>) getIntent().getSerializableExtra("answers");





    }
}
