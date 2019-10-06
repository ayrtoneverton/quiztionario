package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.quiztionario.R;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.QuizService;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	}

	public void searchCode(View view) {
		String code = (((EditText) findViewById(R.id.search_code)).getText().toString());

		try {
			Quiz quiz = QuizService.getInstance(this).searchCode(code);
			Toast.makeText(this, "Quiz Name: " + quiz.getName(),Toast.LENGTH_SHORT).show();
		} catch (ValidationException msg) {
			msg.show(this);
		}

	}

	public void searchText(View view) {
		String text = (((EditText) findViewById(R.id.search_text)).getText().toString());
		try {
			ArrayList<Quiz> result = QuizService.getInstance(this).searchText(text);

		} catch (ValidationException msg) {
			msg.show(this);
		}
	}
}
