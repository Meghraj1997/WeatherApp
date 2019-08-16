package com.example.android.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class add_city_weather  extends AppCompatActivity {

    TextInputEditText city;
    MaterialButton  add;
    TextView        msg;
    RequestQueue re;
    int temp;
    ProgressBar mprogrssBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);

        init();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("TAG","add");
                if(checkUser())
                {
                    int i=getTemp();
                    Log.d("TAG","TEMPof"+i);

                }




            }
        });





    }

        int getTemp(){

        mprogrssBar.setVisibility(View.VISIBLE);


        Log.d("TAG","InMethod Volly");

       final String city1=city.getText().toString();

  //     String url_5days="https://api.openweathermap.org/data/2.5/forecast?q="+city1+",In&units=metric&appid=44661783c185be03349555e3a9144270";

        String url1_currentweather="http://api.openweathermap.org/data/2.5/weather?q="+city1+"&APPID=44661783c185be03349555e3a9144270&units=metric";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1_currentweather, null,
        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.d("TAG","RESPONSE"+temp);

                            JSONObject obj= response.getJSONObject("main");
                            String t= String.valueOf(obj.getDouble("temp"));
                            temp=obj.getInt("temp");
                            Log.d("TAG","RESPONSE"+temp);
                            goBack();





                           /* JSONArray j = response.getJSONArray("list");
                            JSONObject jsonObject4 = j.getJSONObject(0);
                            JSONObject jsonObject11 = (JSONObject) jsonObject4.get("main");
                            txt.setText("Temp" + jsonObject11.getString("temp").toString());
                            */

                        }catch (Exception e)
                        {

                            Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                            mprogrssBar.setVisibility(View.GONE);
                            Log.d("Error","ERROR"+e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("NOTSUCESS", "NOTSUCESS" + error.networkResponse);

                        Toast.makeText(getApplicationContext(),"  City Not Found"+error.networkResponse,Toast.LENGTH_LONG).show();
                        mprogrssBar.setVisibility(View.GONE);



                    }
                });




            re.add(jsonObjectRequest);


            return temp;


    }



    void init()
    {
        city=findViewById(R.id.city_name);
        add=findViewById(R.id.search_city_button);
        msg=findViewById(R.id.message_of_error);
        re=Volley.newRequestQueue(add_city_weather.this);
        mprogrssBar=findViewById(R.id.progrssbarinaddcity);


    }


    void goBack()
    {
        mprogrssBar.setVisibility(View.GONE);

        Intent intent = new Intent();

        //it is use to convert first letter into uppercase
        String str = city.getText().toString();
        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);

        //
        temp=Math.round(temp);

        intent.putExtra("temp", temp);
        intent.putExtra("city",  cap);
        setResult(1, intent);
        finish();


    }


     boolean checkUser()
    {
        Log.d("Tag","i m in chk");
        if(city.getText().toString().isEmpty())
        {
            Log.d("TAG","Empty");

           city.setError("Please Enter City");
            city.requestFocus();
            return false;
        }

        if (city.getText().toString().contains("[a-zA-Z]+") == false && city.getText().toString().length() < 2){

            Log.d("TAG","Its COntaint No");
            city.setError("Not Valid City Name");
            city.requestFocus();
            return false;

        }



          if(isNumeric(city.getText().toString()))
          {
              Log.d("TAG","Number");
              city.setError("Not Valid City Name");
              city.requestFocus();
              return  false;


          }




        return true;




    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            Log.d("TAG","ITsNo");
            return true;
        } catch(NumberFormatException e){
            Log.d("TAG","ExceptionNumber");
            return false;
        }
    }


}
