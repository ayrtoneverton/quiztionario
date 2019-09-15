package com.quiztionario.controller;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.quiztionario.R;
import com.quiztionario.model.Category;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.QuizService;

public class NewQuizActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
		TimePickerDialog.OnTimeSetListener, AutoCompleteAdapter.OnSelectAutoComplete {

	private int idViewDate;
	private GregorianCalendar date;
	private Quiz quiz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_quiz);
		setTitle("New Quiz");
		quiz = new Quiz();
		quiz.setCategory(new Category());
		quiz.setCreator((User) getIntent().getSerializableExtra("user"));

		((AutoCompleteTextView) findViewById(R.id.quiz_category))
				.setAdapter(new AutoCompleteAdapter(this, this));
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		date = new GregorianCalendar(year, month, dayOfMonth);
		Calendar c = Calendar.getInstance();
		new TimePickerDialog(
				NewQuizActivity.this,
				NewQuizActivity.this,
				c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE),
				DateFormat.is24HourFormat(this)
		).show();
	}

	@Override
	@SuppressLint("DefaultLocale")
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		((TextView) findViewById(idViewDate))
				.setText(String.format("%d/%d/%d %d:%d",
						date.get(GregorianCalendar.DAY_OF_MONTH),
						date.get(GregorianCalendar.MONTH),
						date.get(GregorianCalendar.YEAR),
						hourOfDay, minute)
				);
		date.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
		date.set(GregorianCalendar.MINUTE, minute);
		if (idViewDate == R.id.quiz_view_start_date) {
			quiz.setStart(date);
		} else {
			quiz.setEnd(date);
		}
	}

	public void editDate(View view) {
		Calendar c = Calendar.getInstance();
		new DatePickerDialog(
				NewQuizActivity.this,
				NewQuizActivity.this,
				c.get(Calendar.YEAR),
				c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH)
		).show();

		if (view.getId() == R.id.quiz_start_date) {
			idViewDate = R.id.quiz_view_start_date;
		} else {
			idViewDate = R.id.quiz_view_end_date;
		}
	}

	@Override
	public void onSelectAutoComplete(Category item) {
		if (item == null) {
			quiz.setCategory(new Category());
		} else{
			quiz.setCategory(item);
			((AutoCompleteTextView) findViewById(R.id.quiz_category)).setText(item.getName());
		}
	}

	public void submit(View view) {
		try {
			if (quiz.getCategory().getId() == 0)
				quiz.getCategory().setName(((EditText) findViewById(R.id.quiz_category)).getText().toString());
			quiz.setName(((EditText) findViewById(R.id.quiz_name)).getText().toString());

			QuizService.getInstance(this).create(quiz);
			Toast.makeText(getApplicationContext(), "Quiz Created", Toast.LENGTH_LONG).show();
			finish();
		} catch (ValidationException msg) {
			msg.show(this);
		}
	}
}
