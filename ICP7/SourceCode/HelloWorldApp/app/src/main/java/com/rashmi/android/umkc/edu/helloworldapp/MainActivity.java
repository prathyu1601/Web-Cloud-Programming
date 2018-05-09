package com.rashmi.android.umkc.edu.helloworldapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.bday); // get the reference of CalendarView
        String selectedDate = "08/05/2018";
        try {
            simpleCalendarView.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate).getTime(), true, true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleCalendarView.setSelectedWeekBackgroundColor(Color.GRAY);
        simpleCalendarView.setWeekNumberColor(Color.RED);
        long selectedDate1 = simpleCalendarView.getDate();
        TextView txtbday = (TextView) findViewById(R.id.txt_bday);


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(selectedDate1));
        txtbday.setText(dateString);

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                Date date = calendar.getTime();
                String selectedDate = formatter.format(date);
                TextView txtbday = (TextView) findViewById(R.id.txt_bday);
                txtbday.setText(selectedDate);
            }
        });
    }
}
