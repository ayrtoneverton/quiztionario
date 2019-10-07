package com.quiztionario.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.quiztionario.R;
import com.quiztionario.model.Answer;
import com.quiztionario.model.AnswerQuestion;
import com.quiztionario.model.Option;
import com.quiztionario.model.Question;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.User;

public class AnswerQuestionsAdapter extends BaseAdapter implements View.OnClickListener {
	private Answer answer;
	private LayoutInflater inflater;
	private int itemBackgroundResource;

	AnswerQuestionsAdapter(Context context, Quiz quiz, User user) {
		this.inflater = LayoutInflater.from(context);
		this.answer = new Answer(quiz, user);
		for (Question question : quiz.getQuestions()) {
			answer.getAnswers().add(new AnswerQuestion(answer, question));
		}
		TypedValue outValue = new TypedValue();
		context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
		itemBackgroundResource = outValue.resourceId;
	}

	@Override
	@SuppressLint("ViewHolder")
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewGroup v = (ViewGroup) inflater.inflate(R.layout.item_answer_question, viewGroup, false);
		AnswerQuestion answerQuestion = answer.getAnswers().get(i);
		((TextView) v.findViewById(R.id.answer_question_text)).setText(answerQuestion.getQuestion().getText());
		CheckedTextView item;
		for (Option op : answerQuestion.getQuestion().getOptions()) {
			item = (CheckedTextView) inflater.inflate(android.R.layout.simple_list_item_single_choice, viewGroup, false);
			item.setText(op.getText());
			item.setBackgroundResource(itemBackgroundResource);
			item.setTag(new Object[]{answerQuestion, op, v});
			item.setOnClickListener(this);
			v.addView(item);
		}
		return v;
	}

	@Override
	public int getCount() {
		return answer.getAnswers().size();
	}

	@Override
	public Object getItem(int i) {
		return answer.getAnswers().get(i);
	}

	@Override
	public long getItemId(int i) {
		return answer.getAnswers().get(i).getId();
	}

	Answer getAnswer() {
		return answer;
	}

	@Override
	public void onClick(View view) {
		Object[] item = (Object[]) view.getTag();
		((AnswerQuestion) item[0]).setOption((Option) item[1]);
		ViewGroup v = (ViewGroup) item[2];
		for (int i = 1; i < v.getChildCount(); ++i) {
			((CheckedTextView) v.getChildAt(i)).setChecked(false);
		}
		((CheckedTextView) view).setChecked(true);
	}
}
