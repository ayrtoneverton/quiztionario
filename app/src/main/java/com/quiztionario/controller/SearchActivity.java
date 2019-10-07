package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.quiztionario.R;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.QuizService;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
	private User user;
	private ArrayAdapter<Quiz> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		user = (User) getIntent().getSerializableExtra("user");
		arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
		ListView listView = findViewById(R.id.search_list);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(this);
	}

	public void searchCode(View view) {
		String code = ((EditText) findViewById(R.id.search_code)).getText().toString();
		try {
			startActivity(new Intent(view.getContext(), AnswerActivity.class)
					.putExtra("user", user)
					.putExtra("quiz", QuizService.getInstance(this).findByCode(code)));
		} catch (ValidationException msg) {
			msg.show(this);
		}
	}

	public void searchText(View view) {
		String text = ((EditText) findViewById(R.id.search_text)).getText().toString();
		try {
			arrayAdapter.clear();
			arrayAdapter.addAll(QuizService.getInstance(this).findAllByName(text));
			if (arrayAdapter.isEmpty())
				Toast.makeText(this, "No quiz found", Toast.LENGTH_LONG).show();
		} catch (ValidationException msg) {
			msg.show(this);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		startActivity(new Intent(view.getContext(), AnswerActivity.class)
				.putExtra("user", user)
				.putExtra("quiz", arrayAdapter.getItem(i)));
	}
}
