package com.hailv.hnairquality.viewmodel;

import android.databinding.BaseObservable;

import com.hailv.hnairquality.model.AirQModel;

import java.util.List;

import static java.lang.Integer.parseInt;

public class AirQViewModel extends BaseObservable {
    private AirQModel airQModel;

    public AirQViewModel(AirQModel airQModel) {
        this.airQModel = airQModel;
    }

    public String getAqi(){
        return airQModel.getAqi();
    }

    public String getRate(){
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
        return xephang;
    }

    public String getCity(){
        return airQModel.getCity();
    }
    public String getTime(){
        return airQModel.getTime();
    }
}
