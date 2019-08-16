package com.example.android.weatherapp;

import android.widget.TextView;

public class CityDetailData {

   int mmin,mmax,mhumidity,mcloudy;
   String day,mdate;
   double wind;

    public int getMmin() {
        return mmin;
    }

    public void setMmin(int mmin) {
        this.mmin = mmin;
    }

    public int getMmax() {
        return mmax;
    }

    public void setMmax(int mmax) {
        this.mmax = mmax;
    }

    public int getMhumidity() {
        return mhumidity;
    }

    public void setMhumidity(int mhumidity) {
        this.mhumidity = mhumidity;
    }

    public int getMcloudy() {
        return mcloudy;
    }

    public void setMcloudy(int mcloudy) {
        this.mcloudy = mcloudy;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public CityDetailData(int mmin, int mmax, int mhumidity, int mcloudy, String day, String mdate, double wind) {

        this.mmin = mmin;
        this.mmax = mmax;
        this.mhumidity = mhumidity;
        this.mcloudy = mcloudy;
        this.day = day;
        this.mdate = mdate;
        this.wind=wind;

    }
}
