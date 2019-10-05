package com.quiztionario.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.quiztionario.R;
import com.quiztionario.dao.QuizDAO;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
	private User user;
	private MyQuizzesAdapter myQuizzesAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		user = (User) getIntent().getSerializableExtra("user");
		final List<Quiz> listQuizzes = QuizDAO.getInstance(this).findQuizByUser(user);
		myQuizzesAdapter = new MyQuizzesAdapter(this, listQuizzes);

		final ListView listView = new ListView(this);
		listView.setAdapter(myQuizzesAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				startActivity(
						new Intent(view.getContext(), QuestionActivity.class)
								.putExtra("quiz", listQuizzes.get(i)));
			}
		});
		setContentView(listView);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.item_menu_add:
				startActivityForResult(new Intent(this, NewQuizActivity.class).putExtra("user", user), 0);
				return true;
			case R.id.item_menu_search:
				startActivity(new Intent(this, SearchActivity.class));
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (resultCode == Activity.RESULT_OK && data != null)
			myQuizzesAdapter.addQuiz((Quiz) data.getSerializableExtra("quiz"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home_menu, menu);
		return true;
	}
}
