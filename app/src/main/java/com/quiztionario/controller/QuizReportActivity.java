package com.quiztionario.controller;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.quiztionario.R;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.AnswerService;

public class QuizReportActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_report);

		Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");
		try {
			((TextView) findViewById(R.id.quiz_report_quantity_answers)).setText(String.valueOf(
					AnswerService.getInstance(this).countByQuiz(quiz)));
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
}
