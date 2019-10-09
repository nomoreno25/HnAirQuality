package com.hailv.hnairquality;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hailv.hnairquality.adapter.AirQAdapter;
import com.hailv.hnairquality.database.AirQ;
import com.hailv.hnairquality.database.AirQDatabase;
import com.hailv.hnairquality.databinding.ActivityMainBinding;
import com.hailv.hnairquality.model.AirQModel;
import com.hailv.hnairquality.network.ApiClient;
import com.hailv.hnairquality.network.ApiInterface;
import com.hailv.hnairquality.network.ApiResponse;
import com.hailv.hnairquality.network.networkmodel.City;
import com.hailv.hnairquality.network.networkmodel.Data;
import com.hailv.hnairquality.network.networkmodel.Time;
import com.hailv.hnairquality.viewmodel.AirQViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 100;
    private static final String TOKEN = "ab226b024988b9caa70e89d2098c088cbdca5b62";
    private AirQModel airQModel = new AirQModel();
    private AirQViewModel airQViewModel;
    private ActivityMainBinding activityMainBinding;
    private AirQDatabase airQDatabase;
    private List<AirQ> arrDatabase;
    private RecyclerView recyclerView;
    private AirQAdapter airQAdapter;
    private List<AirQViewModel> airQViewModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        airQDatabase = AirQDatabase.getInMemoryDatabase(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView);
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
        } else {
            Log.e("Permission Ready", "Permission Ready");
            getJson();
        }
    }

    public void getJson() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ApiResponse> call = apiService.getApi();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    Data data = response.body().getData();
                    City city = data.getCity();
                    Time time = data.getTime();
                    airQModel.setAqi(data.getAqi().toString());
                    airQModel.setCity(city.getName());
                    airQModel.setTime(time.getS());

                    AirQ airQDBModel = new AirQ();
                    airQDBModel.city = city.getName();
                    airQDBModel.aqi = data.getAqi().toString();
                    airQDBModel.time = time.getS();
                    airQDatabase.airQDAO().insertAirQ(airQDBModel);

                    airQViewModelList = new ArrayList<>();
                    arrDatabase = new ArrayList<>();
                    arrDatabase = airQDatabase.airQDAO().findAllAirQSync();

//                    if (arrDatabase.isEmpty()){
//                        airQDatabase.airQDAO().insertAirQ(airQDBModel);
//                    }

                    for (int i = 0; i < arrDatabase.size(); i++) {
                        airQViewModelList.add(new AirQViewModel(new AirQModel(arrDatabase.get(i).aqi, arrDatabase.get(i).city, arrDatabase.get(i).time)));
                    }
                    airQAdapter = new AirQAdapter(airQViewModelList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(airQAdapter);

                    airQViewModel = new AirQViewModel(airQModel);
                    activityMainBinding.setViewmodel(airQViewModel);
                } else if (response.errorBody() != null) {
                    Log.e("ResponseFailure", "ResponseFailure:" + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("JsonFailure", "JsonFailure:" + t.getMessage());
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
