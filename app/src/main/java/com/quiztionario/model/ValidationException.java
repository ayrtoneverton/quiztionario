package com.quiztionario.model;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ValidationException extends RuntimeException {
	public ValidationException(String msg) {
		super(msg);
	}
	public void show(AppCompatActivity activity){
		Toast.makeText(activity.getApplicationContext(), getMessage(), Toast.LENGTH_LONG).show();
	}
}