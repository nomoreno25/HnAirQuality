package com.hailv.hnairquality.viewmodel;

import com.hailv.hnairquality.model.AirQModel;

import androidx.databinding.BaseObservable;

public class RecyclerViewModel extends BaseObservable {
    private AirQModel airQModel = new AirQModel();

    public RecyclerViewModel(AirQModel airQModel) {
        this.airQModel = airQModel;
    }

    public String nameCityToLV() {
        return airQModel.getmCity();
    }

    public String airIndexToLV() {
        return airQModel.getmIndex();
    }

    public String classificationToLV() {
        return airQModel.getmClassification();
    }

    public String dateTimeToLV() {
        return airQModel.getMdateTime();
    }

}
