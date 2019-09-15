package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import com.quiztionario.R;
import com.quiztionario.dao.QuizDAO;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
	private ArrayList<Quiz> listQuizes;
	private MyQuizAdapter adapter;
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_home);

		user = (User) getIntent().getSerializableExtra("user");
		Toast.makeText(this, "Usuário: " + user.getName(), Toast.LENGTH_SHORT).show();
		listQuizes = new ArrayList<Quiz>();
		listQuizes = QuizDAO.getInstance().findQuizByUser(user);

		ListView listView = new ListView(this);
		setContentView(listView);

		adapter = new MyQuizAdapter(this, listQuizes);
		listView.setAdapter(adapter);
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
			//startActivity(new Intent(this, HomeActivity.class).putExtra("user", user));
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
}
