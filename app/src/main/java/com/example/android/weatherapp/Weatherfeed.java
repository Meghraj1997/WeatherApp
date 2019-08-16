package com.example.android.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Weatherfeed extends AppCompatActivity {

    Button add_city_click;
    RecyclerView mrecyclerView;
   AdapterRecycle adapter;
   ArrayList<data> marraylist;
   SharedPreferences msharedPreference;
   SharedPreferences.Editor meditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_feed);
        init();
        createShared();
        saveShared();




        add_city_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Weatherfeed.this,add_city_weather.class);
                startActivityForResult(intent,1);


            }
        });








    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            Log.e("TAG","M IN ONACTIVITYRESULT");

            if(resultCode==1) {

                int t = data.getIntExtra("temp", 0);
                String city = data.getStringExtra("city");
                data obj = new data(t, city);
                Log.e("TAG", "M IN ONACTIVITYRESULT" + obj.temp);

                marraylist.add(obj);

                adapter = new AdapterRecycle(marraylist, Weatherfeed.this);
                mrecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }









    }


    void init()
    {
        add_city_click=findViewById(R.id.btn);
        mrecyclerView=findViewById(R.id.mrecycleView);
        marraylist=new ArrayList<>();
        mrecyclerView.setAdapter(adapter);
        LinearLayoutManager l=new LinearLayoutManager(this, RecyclerView.VERTICAL,


                false);
        mrecyclerView.setLayoutManager(l);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(Weatherfeed.this, LinearLayoutManager.VERTICAL);
        mrecyclerView.addItemDecoration(mDividerItemDecoration);






    }


    void createShared()
    {
        msharedPreference=getSharedPreferences(Constant.SHARED_PREF_NAME,MODE_PRIVATE);

    }

    void saveShared()
    {

        meditor  = msharedPreference.edit();
        meditor.putBoolean(Constant.LOGIN,Constant.LOGINValue);
        meditor.apply();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater in=getMenuInflater();
        in.inflate(R.menu.menu,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         if(item.getItemId()==R.id.menu_logout)
         {

             meditor=msharedPreference.edit();
             meditor.putBoolean(Constant.LOGOUT,Constant.LOGINValue);
             meditor.apply();
             Intent intent=new Intent(getApplicationContext(),Login.class);
             startActivity(intent);
             finish();


         }

        return true;
    }


    @Override
    public void onBackPressed() {

        Log.d("TAG","FiNish");
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);




    }
}
