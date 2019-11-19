package com.quiztionario.controller;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.quiztionario.R;
import com.quizwork.User;
import com.quizwork.ValidationException;
import com.quiztionario.service.AnswerService;

public class MyReportActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_report);

		User user = (User) getIntent().getSerializableExtra("user");
		try {
			((TextView) findViewById(R.id.my_report_quantity_answers)).setText(String.valueOf(
					AnswerService.getInstance(this).countByUser(user)));
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
}
