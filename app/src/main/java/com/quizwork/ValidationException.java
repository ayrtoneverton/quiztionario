package com.quizwork;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ValidationException extends Exception {
	public ValidationException(String msg) {
		super(msg);
	}

	public void show(AppCompatActivity activity) {
		Toast.makeText(activity.getApplicationContext(), getMessage(), Toast.LENGTH_LONG).show();
	}
}
