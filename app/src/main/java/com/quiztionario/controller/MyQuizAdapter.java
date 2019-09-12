package com.quiztionario.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quiztionario.R;
import com.quiztionario.model.Quiz;

import java.util.List;

public class MyQuizAdapter extends BaseAdapter {
    private Context ctx;
    private List<Quiz> myQuizzesList;

    MyQuizAdapter(Context ctx, List<Quiz> list){
        this.ctx = ctx;
        this.myQuizzesList = list;
    }

    @Override
    public int getCount() { return myQuizzesList.size();}

    @Override
    public Object getItem(int i) { return myQuizzesList.get(i);}

    @Override
    public long getItemId(int i) { return (long) i;}

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Quiz quiz = myQuizzesList.get(i);

        ViewHolder holder;

        View row;


        if(convertView == null){
            Log.d("holder", "Nova posição:" + i);
            row = LayoutInflater.from(ctx).inflate(R.layout.item_quiz, parent, false );
            holder = new ViewHolder(row);
            row.setTag(holder);

        }else{
            Log.d("holder", "Posição Existente:" + i);
            row = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        holder.id.setText(Integer.toString(quiz.getId()));//quiz.getId()));
        holder.name.setText(quiz.getName());

        return row;
    }
}

class ViewHolder{
    final TextView id;
    final TextView name;

    public ViewHolder(View view){
        id = (TextView) view.findViewById(R.id.txtId);
        name = (TextView) view.findViewById(R.id.txtName);

    }
}


