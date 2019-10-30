package com.hailv.hnairquality.database;

import com.hailv.hnairquality.mApplicationContext;
import com.hailv.hnairquality.model.AirQModel;
import com.hailv.hnairquality.viewmodel.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AirQDatabaseModel.class}, version = 1, exportSchema = false)
public abstract class AirQDatabase extends RoomDatabase {
    private static AirQDatabase INSTANCE;
    public abstract AirQDAO airQDAO();

    public static AirQDatabase getInMemoryDatabase(mApplicationContext mApplicationContext) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(mApplicationContext.getAppContext(), AirQDatabase.class)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public void insertDb(String mCity, String mAqi, String mClassification, String mDatetime){
        if (getArrdb().size() == 0){
            AirQDatabase airQDatabase = AirQDatabase.getInMemoryDatabase(new mApplicationContext());
            AirQDatabaseModel airQDatabaseModel = new AirQDatabaseModel();
            airQDatabaseModel.city = mCity;
            airQDatabaseModel.air_index = mAqi;
            airQDatabaseModel.classification = mClassification;
            airQDatabaseModel.date_time = mDatetime;
            airQDatabase.airQDAO().insertAirQ(airQDatabaseModel);
        }
        else {

        }

    }

    public List<AirQDatabaseModel> getArrdb(){
        List<AirQDatabaseModel> arrDb = new ArrayList<>();
        arrDb = AirQDatabase.getInMemoryDatabase(new mApplicationContext()).airQDAO().findAllAirQSync();
        return arrDb;
    }

    public List<RecyclerViewModel> getArrToLv(){
        List<RecyclerViewModel> arrToLv = new ArrayList<>();

        for (int i = 0; i < getArrdb().size(); i++) {
            AirQModel airQModel = new AirQModel();
            airQModel.setmCity(getArrdb().get(i).city);
            airQModel.setmIndex(getArrdb().get(i).air_index);
            airQModel.setMdateTime(getArrdb().get(i).date_time);
            airQModel.setmClassification(getArrdb().get(i).classification);
            RecyclerViewModel recyclerViewModel = new RecyclerViewModel(airQModel);
            arrToLv.add(recyclerViewModel);
        }
        return arrToLv;
    }
}