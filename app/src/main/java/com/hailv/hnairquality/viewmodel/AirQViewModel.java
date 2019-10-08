package com.hailv.hnairquality.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;

import com.hailv.hnairquality.model.AirQModel;

import static java.lang.Integer.parseInt;

public class AirQViewModel extends BaseObservable {
    AirQModel airQModel;

    public AirQViewModel(AirQModel airQModel) {
        this.airQModel = airQModel;
    }

    public String getAqi(){
        int aqi = parseInt(airQModel.getAqi());
        String xephang = "";
        if (aqi>=0){
            xephang = "Good";
            if (aqi>=51){
                xephang = "Moderate";
                if (aqi>=101){
                    xephang = "Unhealthy for sensitive";
                    if (aqi>=151){
                        xephang = "Unhealthy";
                        if (aqi>=201){
                            xephang = "Very unhealthy";
                            if (aqi>=301){
                                xephang = "Hazardous";
                            }
                        }
                    }
                }
            }
        }
        return "Air Quality Index: " + airQModel.getAqi() + "(" + xephang + ")";
    }

    public String getCity(){
        return airQModel.getCity();
    }
    public String getTime(){
        return "Last update: " + airQModel.getTime();
    }
}
