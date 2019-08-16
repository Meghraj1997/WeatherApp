package com.example.android.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CityDetail extends AppCompatActivity {


    ListView listView1;
    BaseAdapter baseAdapter;
    ArrayList<CityDetailData> marraylist;
    RequestQueue re;
  TextView mcity;
   TextView mtemp;
   TextView mcloud;
   ProgressBar mprogressBar;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_detail);

        init();
        getIntentData();

        baseAdapter = new com.example.android.weatherapp.BaseAdapter(marraylist, getApplicationContext());

        listView1.setAdapter(baseAdapter);
        // CityDetailData obj=new CityDetailData("22","26","29hKM","80%","20/2019","20%");
        //marraylist.add(obj);
        Log.d("TAG", "DATA");

       getTemp();



    }

    int getTemp() {

        mprogressBar.setVisibility(View.VISIBLE);

        Log.d("TAG", "InMethod Volly");

        final String city1;

        String url_5days = "https://api.openweathermap.org/data/2.5/forecast?q=" + mcity.getText().toString() + ",In&units=metric&appid=44661783c185be03349555e3a9144270";

        // String url1_currentweather="http://api.openweathermap.org/data/2.5/weather?q="+city1+"&APPID=44661783c185be03349555e3a9144270&units=metric";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_5days, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {

                            Log.d("TAG", "RESPONSE");

                            // JSONObject obj= response.getJSONObject("main");
                           /* String t= String.valueOf(obj.getDouble("temp"));
                            temp=obj.getInt("temp");
                            Log.d("TAG","RESPONSE"+temp);
                            */


                            JSONArray j = response.getJSONArray("list");
                            for (int i = 0; i < j.length(); i = i + 8) {


                                JSONObject jsonObject4 = j.getJSONObject(i);
                                JSONObject jsonObject11 = (JSONObject) jsonObject4.get("main");
                                JSONObject jsonObject12 = (JSONObject) jsonObject4.get("wind");
                                JSONObject jsonObject13 = (JSONObject) jsonObject4.get("clouds");

                                Log.d("TAGjson", "" + jsonObject11.getInt("temp"));
                                int temp = jsonObject11.getInt("temp");

                                Log.d("TempMin", "" + jsonObject11.getInt("temp_min"));

                                int temp_min = jsonObject11.getInt("temp_min");

                                Log.d("TempMax", "" + jsonObject11.getInt("temp_max"));

                                int temp_max = jsonObject11.getInt("temp_max");

                                String d = jsonObject4.getString("dt_txt");
                                Log.d("Date", jsonObject4.getString("dt_txt"));
                               String datenew=d.substring(0,10);

                               Log.d("New",datenew);


                                String dt = chk(d);


                                // int date= Integer.valueOf(jsonObject4.getString("dt_txt"));

                                Log.d("Cloud", "" + jsonObject13.getInt("all"));

                                int clouds = jsonObject13.getInt("all");


                                Double wind = jsonObject12.getInt("speed") * 3.6;

                                Log.d("Wind", "" + wind * 3.6);


                                Log.d("humidity", "" + jsonObject11.getInt("humidity"));

                                int humidity = jsonObject11.getInt("humidity");

                                CityDetailData obj = new CityDetailData(temp_min, temp_max, humidity,  clouds,dt, datenew, wind);
                                marraylist.add(obj);




                            }
                            Log.d("Tag","MKMKMK");
                            mprogressBar.setVisibility(View.GONE);

                            baseAdapter.notifyDataSetChanged();

                            ;
                            //     cloud=%
                            //   "date"


                            //txt.setText("Temp" + jsonObject11.getString("temp").toString());


                        } catch (Exception e) {

                            Log.d("Error", "ERROR" + e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),""+error.networkResponse,Toast.LENGTH_LONG);
                        Log.d("NOTSUCESS", "NOTSUCESS" + error.networkResponse);


                    }
                });


        re.add(jsonObjectRequest);


        return 0;
    }


    void init() {
        listView1 = findViewById(R.id.listView09);
        mcity=findViewById(R.id.cityInDetailView);
        mtemp=findViewById(R.id.degree);
        mcloud=findViewById(R.id.cloudy_check);



        marraylist = new ArrayList<>();

        re = Volley.newRequestQueue(CityDetail.this);
        mprogressBar=findViewById(R.id.progressbarincitydetail);


    }


    String chk(String date_in_string) {
        //09:00:00
        String day="";

        try {


            String str_date = date_in_string;
            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            date = formatter.parse(str_date);


            Log.d("datein ",date_in_string);
             day = dayofweek(date.getDay());


            Log.d("Day", "" + day+"dayNO"+date.getDay());


            String s = "2019-08-16 09:00:00";



           /* Date date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").parse(s);
            Log.d("TAGDate",""+date1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
           Log.d("TAG,",""+calendar.get(Calendar.DAY_OF_WEEK));*/

        } catch (Exception e) {
            Log.d("Tag", e.getMessage());

        }
        return day;

    }








    String dayofweek(int value)
        {
            String day = "";
            switch (value) {
                case 1:
                    day = "Monday";
                    break;
                case 2:
                    day = "Tuesday";
                    break;
                case 3:
                    day = "Wednesday";
                    break;
                case 4:
                    day = "Thursday";
                    break;
                case 5:
                    day = " Friday";
                    break;
                case 6:
                    day = "Saturday";
                    break;
                case 0:
                    day = "Sunday ";
                    break;

            }
            return day;




    }

    void getIntentData()
    {

        Intent intent = getIntent();
        mcity.setText(intent.getStringExtra("city"));
        mtemp.setText(intent.getStringExtra("temp"));
       // mcloud.setText(intent.getStringExtra("cloud"));

    }



}
