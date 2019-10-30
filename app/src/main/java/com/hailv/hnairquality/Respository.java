package com.hailv.hnairquality;

import com.hailv.hnairquality.database.AirQDatabase;
import com.hailv.hnairquality.database.AirQDatabaseModel;
import com.hailv.hnairquality.model.AirQModel;
import com.hailv.hnairquality.server.ApiClient;
import com.hailv.hnairquality.viewmodel.RecyclerViewModel;
import java.util.List;

import androidx.lifecycle.LiveData;

public class Respository implements iRepository {
    private static Respository sRespository;
    private AirQDatabase airQDatabase = AirQDatabase.getInMemoryDatabase(new mApplicationContext());

    public synchronized static Respository getInstance() {
        if (sRespository == null) {
            sRespository = new Respository();
        }
        return sRespository;
    }

    @Override
    public void insertDatabase(String mCity, String mAqi, String mClassification, String mDatetime) {
        airQDatabase.insertDb(mCity, mAqi, mClassification, mDatetime);
    }

    @Override
    public List<AirQDatabaseModel> getDatabase() {
        return airQDatabase.getArrdb();
    }

    @Override
    public List<RecyclerViewModel> getArrToLv() {
        return airQDatabase.getArrToLv();
    }

    @Override
    public LiveData<AirQModel> getDataApi() {
        return ApiClient.getInstance().getDataApi();
    }
}
