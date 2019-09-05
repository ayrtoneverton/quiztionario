package com.quiztionario.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Date;
import com.quiztionario.R;

public class NewQuiz<ab> extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private TextView nTV;
    private TextView nTVI;
    private Button start;
    private Button end;
    private String switchButtonDate;
    private int day, month, year, hour, minute;
    private int dayFinal, monthFinal,  yearFinal, hourFinal, minuteFinal;
    private Date startt;
    private Date endd;

    private Calendar c;
    private DatePickerDialog dpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz);
        setTitle("Criar Quiz");

        nTVI = (TextView)findViewById(R.id.textView4);
        nTV = (TextView)findViewById(R.id.textView6);
        start = (Button)findViewById(R.id.start);
        end = (Button)findViewById(R.id.end);


        start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override

            public void onClick(View v) {
                c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewQuiz.this,
                        NewQuiz.this,year,month,day);
                datePickerDialog.show();
                switchButtonDate = "S";
            }
        } );
        end.setOnClickListener(new View.OnClickListener() {
                                   @RequiresApi(api = Build.VERSION_CODES.N)
                                   @Override
                                   public void onClick(View v) {
                                       c = Calendar.getInstance();
                                       day = c.get(Calendar.DAY_OF_MONTH);
                                       month = c.get(Calendar.MONTH);
                                       year = c.get(Calendar.YEAR);

                                       DatePickerDialog datePickerDialog = new DatePickerDialog(NewQuiz.this,
                                               NewQuiz.this,year,month,day);
                                       datePickerDialog.show();
                                       switchButtonDate = "E";
                                   }
                               }
        );
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month;
        dayFinal = dayOfMonth;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(NewQuiz.this,NewQuiz.this,
                hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;
        if(switchButtonDate.equals("E")) {
            nTVI.setText(dayFinal + "/" + monthFinal + "/" + yearFinal + " " + hourFinal + ":" + minuteFinal);
            endd = new Date(yearFinal,monthFinal,dayFinal,hourFinal,minuteFinal);
        }
        else if(switchButtonDate.equals("S")) {
            nTV.setText(dayFinal + "/" + monthFinal + "/" + yearFinal + " " + hourFinal + ":" + minuteFinal);
            startt = new Date(yearFinal,monthFinal,dayFinal,hourFinal,minuteFinal);
        }
    }
}
