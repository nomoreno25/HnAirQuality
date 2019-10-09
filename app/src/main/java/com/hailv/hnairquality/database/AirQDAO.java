package com.hailv.hnairquality.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface AirQDAO {
    @Insert(onConflict = REPLACE)
    void insertAirQ(AirQ airQ);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceAirQ(AirQ... airQS);

    @Update(onConflict = REPLACE)
    void updateAirQ(AirQ airQ);

    @Query("DELETE FROM AirQ")
    void deleteAll();

    @Query("SELECT * FROM AirQ")
    public List<AirQ> findAllAirQSync();
}
