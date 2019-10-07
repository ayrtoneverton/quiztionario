package com.quiztionario.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.quiztionario.R;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.QuizService;

public class CreateQuestionsActivity extends AppCompatActivity {
	private Quiz quiz;
	private CreateQuestionsAdapter questionsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_questions);

		quiz = (Quiz) getIntent().getSerializableExtra("quiz");
		questionsAdapter = new CreateQuestionsAdapter(this);
		((ListView) findViewById(R.id.create_question_list)).setAdapter(questionsAdapter);
	}

	public void addQuestion(View view) {
		questionsAdapter.showDialog(null);
	}

	public void submit(View view) {
		try {
			quiz.setQuestions(questionsAdapter.getQuestions());
			quiz = QuizService.getInstance(this).create(quiz);
			Toast.makeText(getApplicationContext(), "Quiz Created", Toast.LENGTH_LONG).show();
			setResult(Activity.RESULT_OK, new Intent().putExtra("quiz", quiz));
			finish();
		} catch (ValidationException msg) {
			msg.show(this);
		}
	}
}
