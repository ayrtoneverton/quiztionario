package com.quiztionario.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.quiztionario.R;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

public class AnswerActivity extends AppCompatActivity {
	private AnswerQuestionsAdapter answerQuestionsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);

		final Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");
		final User user = (User) getIntent().getSerializableExtra("user");
		((TextView) findViewById(R.id.answer_quiz_name)).setText(quiz.getName());
		answerQuestionsAdapter = new AnswerQuestionsAdapter(this, quiz, user);
		((ListView) findViewById(R.id.answer_questions)).setAdapter(answerQuestionsAdapter);
	}

	public void save(View view) {
		answerQuestionsAdapter.getAnswer();
	}
}
