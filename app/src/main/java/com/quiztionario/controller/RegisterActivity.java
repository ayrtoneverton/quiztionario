package com.quiztionario.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.quiztionario.R;
import com.quizwork.User;
import com.quizwork.ValidationException;
import com.quiztionario.service.UserService;

public class RegisterActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		getSupportActionBar().hide();
	}

	public void register(View view) {
		try {
			User user = UserService.getInstance(this).create(
					new User(
							((EditText) findViewById(R.id.register_name)).getText().toString(),
							((EditText) findViewById(R.id.register_email)).getText().toString(),
							((EditText) findViewById(R.id.register_password)).getText().toString()
					)
			);
			setResult(Activity.RESULT_OK, new Intent().putExtra("user", user));
			finish();
		} catch (ValidationException msg) {
			msg.show(this);
		}
	}
}
