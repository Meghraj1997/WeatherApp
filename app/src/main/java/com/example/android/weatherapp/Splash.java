package com.example.android.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    SharedPreferences msharedPreferences;
    SharedPreferences.Editor medt;
    boolean mlogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setShared();
        mlogin = msharedPreferences.getBoolean(Constant.LOGIN, false);

        if (mlogin) {
            Intent intent =new  Intent(this, Weatherfeed.class);
            startActivity(intent);



        } else {
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            ///editor?.putBoolean(Constant.SHARED_PREF_NAME,true);


        }


    }

    void  setShared() {

        msharedPreferences = getSharedPreferences(Constant.SHARED_PREF_NAME,  MODE_PRIVATE);



    }

}
