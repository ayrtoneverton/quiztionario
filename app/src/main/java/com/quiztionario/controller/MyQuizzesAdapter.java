package com.quiztionario.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quiztionario.R;
import com.quiztionario.model.Quiz;

import java.util.List;

public class MyQuizzesAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<Quiz> quizzes;

	MyQuizzesAdapter(Context context, List<Quiz> quizzes) {
		this.inflater = LayoutInflater.from(context);
		this.quizzes = quizzes;
	}

	@Override
	public View getView(int i, View convertView, ViewGroup parent) {
		Quiz quiz = quizzes.get(i);
		View row;
		if (convertView == null) {
			row = inflater.inflate(R.layout.item_quiz, parent, false);
		} else {
			row = convertView;
		}
		((TextView) row.findViewById(R.id.item_quiz_name)).setText(quiz.getName());
		((TextView) row.findViewById(R.id.item_quiz_open)).setText(quiz.isOpen() ? "Public" : "Local Code: " + quiz.getCode());

		return row;
	}

	public void addQuiz(Quiz quiz) {
		quizzes.add(quiz);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return quizzes.size();
	}

	@Override
	public Object getItem(int i) {
		return quizzes.get(i);
	}

	@Override
	public long getItemId(int i) {
		return (long) i;
	}
}
