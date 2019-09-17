package com.quiztionario.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.quiztionario.R;
import com.quiztionario.model.Option;
import com.quiztionario.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionsAdapter extends BaseAdapter implements View.OnClickListener {
	private List<Question> questions = new ArrayList<>();
	private LayoutInflater inflater;

	CreateQuestionsAdapter(Context context) {
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	@SuppressLint("ViewHolder")
	public View getView(int i, View view, ViewGroup viewGroup) {
		View v = inflater.inflate(R.layout.item_create_question, viewGroup, false);
		((TextView) v.findViewById(R.id.create_question_text)).setText(questions.get(i).getText());
		View add = v.findViewById(R.id.create_question_add_option);
		add.setOnClickListener(this);
		add.setTag(i);
		View remove = v.findViewById(R.id.create_question_remove);
		remove.setOnClickListener(this);
		remove.setTag(i);
		ListView optionsView = v.findViewById(R.id.create_question_options);
		optionsView.setAdapter(new ArrayAdapter<>(
				inflater.getContext(),
				android.R.layout.simple_list_item_multiple_choice,
				questions.get(i).getOptions()));
		return v;
	}

	@Override
	public void onClick(View view) {
		int i = (int) view.getTag();
		switch (view.getId()) {
			case R.id.create_question_add_option:
				showDialog(questions.get(i));
				break;
			case R.id.create_question_remove:
				questions.remove(i);
				notifyDataSetChanged();
		}
	}

	void showDialog(final Question question) {
		final EditText input = new EditText(inflater.getContext());
		new AlertDialog.Builder(inflater.getContext())
				.setTitle("New " + (question == null ? "Question" : "Option"))
				.setView(input)
				.setPositiveButton("Add", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (question == null) {
							questions.add(new Question(input.getText().toString()));
						} else {
							question.getOptions().add(new Option(input.getText().toString(), question));
						}
						notifyDataSetChanged();
					}
				})
				.show();
	}

	@Override
	public int getCount() {
		return questions.size();
	}

	@Override
	public Object getItem(int i) {
		return questions.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	public List<Question> getQuestions() {
		return questions;
	}
}
