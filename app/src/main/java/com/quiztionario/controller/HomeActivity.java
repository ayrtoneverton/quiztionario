package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


import com.quiztionario.R;
import com.quiztionario.model.Quiz;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
	private ArrayList<Quiz> listQuizes;
	private MyQuizAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_home);
		listQuizes = new ArrayList<Quiz>();

		Quiz quiz1 = new Quiz();
		quiz1.setId(1);
		quiz1.setName("Quiz 1");
		listQuizes.add(quiz1);

		Quiz quiz2 = new Quiz();
		quiz2.setId(2);
		quiz2.setName("Quiz 2");
		listQuizes.add(quiz2);

		Quiz quiz3 = new Quiz();
		quiz3.setId(3);
		quiz3.setName("Quiz 3");
		listQuizes.add(quiz3);

		Quiz quiz4 = new Quiz();
		quiz4.setId(4);
		quiz4.setName("Quiz 4");
		listQuizes.add(quiz4);

		Quiz quiz5 = new Quiz();
		quiz5.setId(5);
		quiz5.setName("Quiz 5");
		listQuizes.add(quiz5);

		Quiz quiz6 = new Quiz();
		quiz6.setId(6);
		quiz6.setName("Quiz 6");
		listQuizes.add(quiz6);

		Quiz quiz7 = new Quiz();
		quiz7.setId(7);
		quiz7.setName("Quiz 7");
		listQuizes.add(quiz7);

		Quiz quiz8 = new Quiz();
		quiz8.setId(8);
		quiz8.setName("Quiz 8");
		listQuizes.add(quiz8);

		Quiz quiz9 = new Quiz();
		quiz9.setId(9);
		quiz9.setName("Quiz 9");
		listQuizes.add(quiz9);

		Quiz quiz10 = new Quiz();
		quiz10.setId(10);
		quiz10.setName("Quiz 10");
		listQuizes.add(quiz10);

		Quiz quiz11 = new Quiz();
		quiz11.setId(11);
		quiz11.setName("Quiz 11");
		listQuizes.add(quiz11);

		Quiz quiz12 = new Quiz();
		quiz12.setId(12);
		quiz12.setName("Quiz 12");
		listQuizes.add(quiz12);

		Quiz quiz13 = new Quiz();
		quiz13.setId(13);
		quiz13.setName("Quiz 13");
		listQuizes.add(quiz13);

		Quiz quiz14 = new Quiz();
		quiz14.setId(14);
		quiz14.setName("Quiz 14");
		listQuizes.add(quiz14);

		Quiz quiz15 = new Quiz();
		quiz15.setId(15);
		quiz15.setName("Quiz 15");
		listQuizes.add(quiz15);

		Quiz quiz16 = new Quiz();
		quiz16.setId(16);
		quiz16.setName("Quiz 16");
		listQuizes.add(quiz16);

		Quiz quiz17 = new Quiz();
		quiz17.setId(17);
		quiz17.setName("Quiz 17");
		listQuizes.add(quiz17);

		Quiz quiz18 = new Quiz();
		quiz18.setId(18);
		quiz18.setName("Quiz 18");
		listQuizes.add(quiz18);

		Quiz quiz19 = new Quiz();
		quiz19.setId(19);
		quiz19.setName("Quiz 19");
		listQuizes.add(quiz19);

		Quiz quiz20 = new Quiz();
		quiz20.setId(20);
		quiz20.setName("Quiz 20");
		listQuizes.add(quiz20);

		Quiz quiz21 = new Quiz();
		quiz21.setId(21);
		quiz21.setName("Quiz 21");
		listQuizes.add(quiz21);

		Quiz quiz22 = new Quiz();
		quiz22.setId(22);
		quiz22.setName("Quiz 22");
		listQuizes.add(quiz22);

		Quiz quiz23 = new Quiz();
		quiz23.setId(23);
		quiz23.setName("Quiz 23");
		listQuizes.add(quiz23);

		Quiz quiz24 = new Quiz();
		quiz24.setId(24);
		quiz24.setName("Quiz 24");
		listQuizes.add(quiz24);

		Quiz quiz25 = new Quiz();
		quiz25.setId(25);
		quiz25.setName("Quiz 25");
		listQuizes.add(quiz25);

		Quiz quiz26 = new Quiz();
		quiz26.setId(26);
		quiz26.setName("Quiz 26");
		listQuizes.add(quiz26);

		Quiz quiz27 = new Quiz();
		quiz27.setId(27);
		quiz27.setName("Quiz 27");
		listQuizes.add(quiz27);

		Quiz quiz28 = new Quiz();
		quiz28.setId(28);
		quiz28.setName("Quiz 28");
		listQuizes.add(quiz28);

		Quiz quiz29 = new Quiz();
		quiz29.setId(29);
		quiz29.setName("Quiz 29");
		listQuizes.add(quiz29);



		ListView listView = new ListView(this);
		setContentView(listView);

		adapter = new MyQuizAdapter(this, listQuizes);
		listView.setAdapter(adapter);

		// User user = (User) getIntent().getSerializableExtra("user");
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
			startActivity(new Intent(this, NewQuizActivity.class));
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
}
