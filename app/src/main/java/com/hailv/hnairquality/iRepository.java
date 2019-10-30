package com.hailv.hnairquality;

import com.hailv.hnairquality.database.AirQDatabaseModel;
import com.hailv.hnairquality.model.AirQModel;
import com.hailv.hnairquality.viewmodel.RecyclerViewModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface iRepository {
    void insertDatabase(String mCity, String mAqi, String mClassification, String mDatetime);
    List<AirQDatabaseModel> getDatabase();
    List<RecyclerViewModel> getArrToLv();
    LiveData<AirQModel> getDataApi();
}
