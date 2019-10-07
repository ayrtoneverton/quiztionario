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

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
	private User user;
	private MyQuizzesAdapter myQuizzesAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		user = (User) getIntent().getSerializableExtra("user");
		myQuizzesAdapter = new MyQuizzesAdapter(this, QuizDAO.getInstance(this).findQuizByUser(user));

		final ListView listView = new ListView(this);
		listView.setAdapter(myQuizzesAdapter);
		listView.setOnItemClickListener(this);
		setContentView(listView);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.item_menu_add:
				startActivityForResult(new Intent(this, NewQuizActivity.class).putExtra("user", user), 0);
				return true;
			case R.id.item_menu_search:
				startActivity(new Intent(this, SearchActivity.class).putExtra("user", user));
				return true;
			case R.id.item_menu_my_report:
				startActivity(new Intent(this, MyReportActivity.class).putExtra("user", user));
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && data != null)
			myQuizzesAdapter.addQuiz((Quiz) data.getSerializableExtra("quiz"));
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		/*startActivity(new Intent(view.getContext(), AnswerActivity.class)
				.putExtra("user", user)
				.putExtra("quiz", (Quiz) myQuizzesAdapter.getItem(i)));*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home_menu, menu);
		return true;
	}
}
