package com.example.android.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar c = Calendar.getInstance();

        Date someDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("2019-08-18");
        String s = df.format(someDate);
        System.out.print(s);






    }


}
