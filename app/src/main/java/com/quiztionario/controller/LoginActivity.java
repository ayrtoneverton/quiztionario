package com.quiztionario.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.quiztionario.R;
import com.quiztionario.model.User;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.UserService;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getSupportActionBar().hide();
	}

	public void submit(View view) {
		try {
			User user = UserService.login(
				((EditText) findViewById(R.id.login_email)).getText().toString(),
				((EditText) findViewById(R.id.login_password)).getText().toString()
			);
			showHome(user);
		} catch (ValidationException msg) {
			msg.show(this);
		}
	}

	public void register(View view) {
		startActivityForResult(new Intent(this, RegisterActivity.class), 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			showHome((User) data.getSerializableExtra("user"));
		}
	}

	private void showHome(User user) {
		startActivity(
			new Intent(this, HomeActivity.class)
				.putExtra("user", user)
		);
	}
}
