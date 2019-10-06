package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.quiztionario.R;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.QuizService;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		user = (User) getIntent().getSerializableExtra("user");
	}

	public void searchCode(View view) {
		String code = ((EditText) findViewById(R.id.search_code)).getText().toString();
		try {
			startActivity(new Intent(view.getContext(), AnswerActivity.class)
					.putExtra("user", user)
					.putExtra("quiz", QuizService.getInstance(this).searchCode(code)));
		} catch (ValidationException msg) {
			msg.show(this);
		}
	}

	public void searchText(View view) {
		String text = ((EditText) findViewById(R.id.search_text)).getText().toString();
		try {
			ArrayList<Quiz> result = QuizService.getInstance(this).searchText(text);

		} catch (ValidationException msg) {
			msg.show(this);
		}
	}
}
