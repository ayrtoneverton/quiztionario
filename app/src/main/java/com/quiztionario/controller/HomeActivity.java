package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.quiztionario.R;
import com.quiztionario.dao.QuizDAO;
import com.quiztionario.model.Question;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

import java.io.Serializable;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_home);

		user = (User) getIntent().getSerializableExtra("user");
		final List<Quiz> listQuizzes = QuizDAO.getInstance(this).findQuizByUser(user);

		final ListView listView = new ListView(this);
		listView.setAdapter(new MyQuizAdapter(this, listQuizzes));
		setContentView(listView);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Quiz quiz = listQuizzes.get(i);
				Toast.makeText(view.getContext(),quiz.getName(),Toast.LENGTH_SHORT).show();
				startActivity(new Intent(view.getContext(), QuestionActivity.class).putExtra("quiz", quiz));
			}
		});
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
