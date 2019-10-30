package com.hailv.hnairquality.model;

public class AirQModel {
    public String mCity, mIndex, mClassification,mdateTime;

    public AirQModel(){

    }

    public AirQModel(String mCity, String mIndex, String mClassification, String mdateTime) {
        this.mCity = mCity;
        this.mIndex = mIndex;
        this.mClassification = mClassification;
        this.mdateTime = mdateTime;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmIndex() {
        return mIndex;
    }

    public void setmIndex(String mIndex) {
        this.mIndex = mIndex;
    }

    public String getmClassification() {
        return mClassification;
    }

    public void setmClassification(String mClassification) {
        this.mClassification = mClassification;
    }

    public String getMdateTime() {
        return mdateTime;
    }

    public void setMdateTime(String mdateTime) {
        this.mdateTime = mdateTime;
    }
}
