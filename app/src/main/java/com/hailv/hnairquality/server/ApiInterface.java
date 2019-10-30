package com.hailv.hnairquality.server;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    String BASE_URL ="https://api.waqi.info";
    @GET("/feed/hanoi/?token=ab226b024988b9caa70e89d2098c088cbdca5b62")
    Call<ApiResponse> getDataApi();
}
