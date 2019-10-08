package com.hailv.hnairquality.model;

public class AirQModel {
    String aqi, city, time;

    public AirQModel(){

    }

    public AirQModel(String aqi, String city, String time) {
        this.aqi = aqi;
        this.city = city;
        this.time = time;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
