package com.hailv.hnairquality.server.networkmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class No2 {

    @SerializedName("v")
    @Expose
    private Double v;

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

}