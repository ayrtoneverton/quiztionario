package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.quiztionario.R;
import com.quiztionario.model.User;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		User user = (User) getIntent().getSerializableExtra("user");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.item_quiz:
				//Chamar activity do quiz
				Toast.makeText(this, "O botão quiz foi selecionado.", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.item_1:
				Toast.makeText(this, "O botão item 1 foi selecionado.", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.item_2:
				Toast.makeText(this, "O botão item 2 foi selecionado.", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.subitem1:
				Toast.makeText(this, "O botão sub item 1 foi selecionado.", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.subitem2:
				Toast.makeText(this, "O botão sub item 2 foi selecionado.", Toast.LENGTH_SHORT).show();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}

	}
}
