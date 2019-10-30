package com.hailv.hnairquality.server;

import com.google.gson.GsonBuilder;
import com.hailv.hnairquality.model.AirQModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private ApiInterface apiInterface;
    private static ApiClient apiClient;

    public ApiInterface getApiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
        return apiInterface;
    }

    public synchronized static ApiClient getInstance() {
        if (apiClient == null) {
            if (apiClient == null) {
                apiClient = new ApiClient();
            }
        }
        return apiClient;
    }

    public LiveData<AirQModel> getDataApi() {
        final MutableLiveData<AirQModel> data = new MutableLiveData<>();
        getApiInterface().getDataApi().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                AirQModel airQModel = new AirQModel();
                airQModel.setmCity(response.body().getData().getCity().getName());
                airQModel.setmIndex(String.valueOf(response.body().getData().getAqi()));
                airQModel.setmClassification(processClassifition(response.body().getData().getAqi()));
                airQModel.setMdateTime(convertDatetime(response.body().getData().getTime().getS()));
                data.setValue(airQModel);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public String processClassifition(Integer mIndex){
        String mClassification;
        if (mIndex >= 0) {
            mClassification= "Good";
            return mClassification;
        } else if (mIndex >= 51) {
            mClassification ="Moderate";
            return mClassification;
        } else if (mIndex >= 101) {
            mClassification = "Unhealthy for sensitive";
            return mClassification;
        } else if (mIndex >= 151) {
            mClassification = "Unhealthy";
            return mClassification;
        } else if (mIndex >= 201) {
            mClassification = "Very unhealthy";
            return mClassification;
        } else if (mIndex >= 301) {
            mClassification = "Hazardous";
            return mClassification;
        }
        return "classification";
    }

    public String convertDatetime(String inputDateStr) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat outputFormat = new SimpleDateFormat("hh:mm:ss dd-MMM-yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        return outputDateStr;
    }
}
