package com.quiztionario.controller;


import android.view.View;
import android.widget.TextView;

import com.quiztionario.R;

public class ViewHolder{
    final TextView id;
    final TextView name;

    public ViewHolder(View view){
        id = (TextView) view.findViewById(R.id.txtId);
        name = (TextView) view.findViewById(R.id.txtName);

    }
}