package com.hailv.hnairquality;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hailv.hnairquality.databinding.ActivityMainBinding;
import com.hailv.hnairquality.model.AirQModel;
import com.hailv.hnairquality.network.ApiClient;
import com.hailv.hnairquality.network.ApiInterface;
import com.hailv.hnairquality.network.ApiResponse;
import com.hailv.hnairquality.network.networkmodel.City;
import com.hailv.hnairquality.network.networkmodel.Data;
import com.hailv.hnairquality.network.networkmodel.Time;
import com.hailv.hnairquality.viewmodel.AirQViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 100;
    private static final String TOKEN = "ab226b024988b9caa70e89d2098c088cbdca5b62";
    private AirQModel airQModel = new AirQModel();
    private AirQViewModel airQViewModel;
    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //check permisssion
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.INTERNET))) {

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.INTERNET},
                        REQUEST_PERMISSIONS);
            }
        }else {
            Log.e("Else","Else");
            getJson();
            Log.e("afterjson", airQModel.getAqi()+"");
            Log.e("afterjson", airQModel.getCity()+"");
            Log.e("afterjson", airQModel.getTime()+"");

        }
    }

    public void getJson(){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ApiResponse> call = apiService.getApi();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse>call, Response<ApiResponse> response) {
                if(response.isSuccessful()){
                    Data data = response.body().getData();
                    City city = data.getCity();
                    Time time = data.getTime();
                    airQModel.setAqi(data.getAqi().toString());
                    airQModel.setCity(city.getName());
                    airQModel.setTime(time.getS());
                    airQViewModel = new AirQViewModel(airQModel);
                    activityMainBinding.setViewmodel(airQViewModel);
                    Log.e("data", airQModel.getAqi()+"");
                    Log.e("data", airQModel.getCity()+"");
                    Log.e("data", airQModel.getTime()+"");
                }else if (response.errorBody() != null){
                    Log.e("ResponseFailure", "ResponseFailure:"+response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("JsonFailure", "JsonFailure:"+t.getMessage());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        getJson();
                    } else {
                        Toast.makeText(MainActivity.this, "Error permission!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
