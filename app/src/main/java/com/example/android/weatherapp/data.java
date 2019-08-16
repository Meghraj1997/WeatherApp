package com.example.android.weatherapp;

public class data {

    int temp;
    String cityName;

    public data(int temp, String cityName) {
        this.temp = temp;
        this.cityName = cityName;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
