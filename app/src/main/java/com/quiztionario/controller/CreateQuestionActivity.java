package com.quiztionario.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.quiztionario.R;
import com.quiztionario.model.Option;
import com.quiztionario.model.Question;
import com.quiztionario.model.Quiz;
import com.quiztionario.model.ValidationException;
import com.quiztionario.service.OptionService;

import java.util.ArrayList;

public class CreateQuestionActivity extends AppCompatActivity {

    private Question question;
    private Quiz quiz;
    private String correctOption;
    private ArrayList<Option> options;
    private ArrayList<String> optionText;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        options = new ArrayList<Option>();
        final ArrayList<String> optionText= new ArrayList<String>();

        listView = (ListView) findViewById(R.id.listOption);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,optionText);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),optionText.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addOptionToQuestion(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Option");
        // DECLARACAO DO EDITTEXT
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setNeutralButton("Done", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Option option = new Option();
                option.setQuestion(question);
                option.setText(input.getText().toString());
                adapter.add(option.getText());
                try {
                    Option optionb = OptionService.getInstance().create(option);
                    options.add(optionb);

                } catch (ValidationException msg) {
                    msg.getMessage();
                }
            }
        });
        builder.show();
        // FORÇA O TECLADO APARECER AO ABRIR O ALERT
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    public void createOption(){

    }
    public void saveQuestion(){}
}
