package com.hailv.hnairquality.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AirQDAO {
    @Insert(onConflict = REPLACE)
    void insertAirQ(AirQDatabaseModel airQDatabaseModel);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceAirQ(AirQDatabaseModel... airQDatabaseModels);

    @Update(onConflict = REPLACE)
    void updateAirQ(AirQDatabaseModel airQDatabaseModel);

    @Query("DELETE FROM AirQDatabaseModel")
    void deleteAll();

    @Query("SELECT * FROM AirQDatabaseModel")
    public List<AirQDatabaseModel> findAllAirQSync();
}
