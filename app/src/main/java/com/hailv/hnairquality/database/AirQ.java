package com.hailv.hnairquality.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class AirQ {
    @PrimaryKey(autoGenerate = true)
    public long cityId;
    @ColumnInfo
    public String city;
    public String aqi;
    public String time;
}
