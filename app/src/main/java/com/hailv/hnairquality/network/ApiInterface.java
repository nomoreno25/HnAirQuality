package com.hailv.hnairquality.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("feed/hanoi/?token=ab226b024988b9caa70e89d2098c088cbdca5b62")
    Call<ApiResponse> getApi();
}
