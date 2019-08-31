package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.quiztionario.R;
import com.quiztionario.model.User;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		User user = (User) getIntent().getSerializableExtra("user");
	}
}
