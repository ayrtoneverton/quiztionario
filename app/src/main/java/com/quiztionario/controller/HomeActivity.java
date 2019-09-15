package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


import com.quiztionario.R;
import com.quiztionario.dao.QuizDAO;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_home);

		user = (User) getIntent().getSerializableExtra("user");
		List<Quiz> listQuizzes = QuizDAO.getInstance(this).findQuizByUser(user);

		ListView listView = new ListView(this);
		listView.setAdapter(new MyQuizAdapter(this, listQuizzes));
		setContentView(listView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.item_menu_add) {
			startActivity(new Intent(this, NewQuizActivity.class).putExtra("user", user));
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
}
