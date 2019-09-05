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
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.quiztionario.R;

public class NewQuizActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

	private int idViewDate;
	private GregorianCalendar date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_quiz);
		setTitle("New Quiz");
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

	public void submit(View view) {

	}
}
