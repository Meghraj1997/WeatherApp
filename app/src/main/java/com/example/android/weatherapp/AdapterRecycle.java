package com.example.android.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.view>   {

    ArrayList<data> marraylist;


    public AdapterRecycle(ArrayList<data> marraylist, Context c) {
        this.marraylist = marraylist;
        this.c = c;
    }

    Context c;

    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(c);

        // Inflate the layout

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview,null);

        return new view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final view holder, int position) {

        holder.city.setText(marraylist.get(position).cityName);
        holder.temp1.setText(String.valueOf(marraylist.get(position).temp)+"c");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(c,CityDetail.class);
                intent.putExtra("city",holder.city.getText().toString());
                intent.putExtra("temp",holder.temp1.getText().toString());

                c.startActivity(intent);



            }
        });

        Log.e("TAG","M IN ONBIND()");





    }

    @Override
    public int getItemCount() {
        return marraylist.size();
    }


    class view extends RecyclerView.ViewHolder{


       TextView city;
       TextView temp1;
       public view(@NonNull View itemView) {
           super(itemView);

           city=itemView.findViewById(R.id.city_name_view);
           temp1=itemView.findViewById(R.id.mtemp_view);






       }
   }


}
