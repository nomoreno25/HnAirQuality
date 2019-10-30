package com.hailv.hnairquality.viewmodel;

import android.app.Application;

import com.hailv.hnairquality.Respository;
import com.hailv.hnairquality.model.AirQModel;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AirQViewModel extends AndroidViewModel {
    final LiveData<AirQModel> airQLiveData;
    public ObservableField<AirQModel> airQModelObservableField = new ObservableField<>();

    public AirQViewModel(Application application) {
        super(application);
        airQLiveData = Respository.getInstance().getDataApi();
    }

    public LiveData<AirQModel> getObservableProject() {
        return airQLiveData;
    }

    public void setAirQViewModel(AirQModel airQModel) {
        this.airQModelObservableField.set(airQModel);
        insertDb();
    }

    public String nameCity() {
        return airQModelObservableField.get().getmCity();
    }

    public String airIndex() {
        return airQModelObservableField.get().getmIndex();
    }

    public String classification() {
        return airQModelObservableField.get().getmClassification();
    }

    public String dateTime() {
        return airQModelObservableField.get().getMdateTime();
    }

    public void insertDb() {
        Respository.getInstance().insertDatabase(nameCity(), airIndex(), classification(), dateTime());
    }

    public List<RecyclerViewModel> getArrToLv() {
        return Respository.getInstance().getArrToLv();
    }

}