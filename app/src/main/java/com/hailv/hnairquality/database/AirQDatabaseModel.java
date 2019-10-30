package com.hailv.hnairquality.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AirQDatabaseModel {
    @PrimaryKey(autoGenerate = true)
    public long cityId;
    @ColumnInfo
    public String city;
    public String air_index;
    public String classification;
    public String date_time;
}
