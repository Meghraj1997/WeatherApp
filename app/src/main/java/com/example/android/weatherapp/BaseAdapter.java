package com.example.android.weatherapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseAdapter extends android.widget.BaseAdapter {

      ArrayList<CityDetailData> marraylist;
      Context c;

    public BaseAdapter(ArrayList<CityDetailData> cityDetail,Context context) {
        Log.d("TAG","BaseAdapter");
        c=context;
        this.marraylist = cityDetail;


    }

    @Override
    public int getCount() {
        return marraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return marraylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("TAG","DATA09");

        if (view == null) {
            view =LayoutInflater.from(c).
                    inflate(R.layout.day_detail, null, false);
        }

       CityDetailData item=(CityDetailData) getItem(i);

        TextView min=view.findViewById(R.id.minTempEdtTxt);
        TextView max=view.findViewById(R.id.maxTempEdtTxt);
        TextView wind=view.findViewById(R.id.windEdtTxt);
        TextView humidity=view.findViewById(R.id.humidityEdtTxt);
        TextView date=view.findViewById(R.id.dateEdtTxt);
        TextView cloudy=view.findViewById(R.id.cloudyEditPercent);
        TextView day=view.findViewById(R.id.day);

        //set text


        min.setText(String.valueOf(item.getMmin()));
        max.setText(String.valueOf(item.getMmax()));
        int lp= (int)item.getWind();
        wind.setText(String.valueOf(lp));
        humidity.setText(String.valueOf(item.getMhumidity()));
        date.setText(String.valueOf(item.getMdate()));
        cloudy.setText(String.valueOf(item.getMcloudy()));
        day.setText(item.getDay().toString());






        Log.d("TAG","on view");

        return  view;

    }
}
