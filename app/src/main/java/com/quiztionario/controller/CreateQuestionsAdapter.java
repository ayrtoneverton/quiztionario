package com.quiztionario.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.quiztionario.R;
import com.quiztionario.model.ObjectiveQuestion;
import com.quiztionario.model.Option;
import com.quizwork.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionsAdapter extends BaseAdapter implements View.OnClickListener, AdapterView.OnItemClickListener {
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
				android.R.layout.simple_list_item_single_choice,
				((ObjectiveQuestion) questions.get(i)).getOptions()));
		optionsView.setOnItemClickListener(this);
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
		input.setSingleLine(true);
		new AlertDialog.Builder(inflater.getContext())
				.setTitle("New " + (question == null ? "Question" : "Option"))
				.setView(input)
				.setPositiveButton("Add", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String text = input.getText().toString();
						if (text.isEmpty())
							return;
						if (question == null) {
							questions.add(new ObjectiveQuestion(text));
							Toast.makeText(inflater.getContext(), "Question added", Toast.LENGTH_LONG).show();
						} else {
							((ObjectiveQuestion) question).getOptions().add(new Option(text, question));
							Toast.makeText(inflater.getContext(), "Option added", Toast.LENGTH_LONG).show();
						}
					}
				})
				.show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
		Option op = (Option) parent.getItemAtPosition(i);
		op.getQuestion().setCorrect(op);
		for (int j = 0; j < parent.getChildCount(); ++j) {
			((CheckedTextView) parent.getChildAt(j)).setChecked(false);
		}
		((CheckedTextView) view).setChecked(true);
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

	List<Question> getQuestions() {
		return questions;
	}
}
