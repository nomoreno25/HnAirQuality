package com.hailv.hnairquality.network;

import com.google.gson.annotations.SerializedName;
import com.hailv.hnairquality.network.networkmodel.Data;

import java.util.List;

public class ApiResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Data data){
        this.data = data;
    }

    public Data getData(){
        return data;
    }

    @Override
    public String toString(){
        return
                "ApiResponse{" +
                        "Status = '" + status + '\'' +
                        "Data = '" + data + '\'' +
                        "}";
    }
}
