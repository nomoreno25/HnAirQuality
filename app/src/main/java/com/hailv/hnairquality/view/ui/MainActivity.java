package com.hailv.hnairquality.view.ui;


import android.os.Bundle;

import com.hailv.hnairquality.R;
import com.hailv.hnairquality.databinding.ActivityMainBinding;
import com.hailv.hnairquality.model.AirQModel;
import com.hailv.hnairquality.server.ApiClient;
import com.hailv.hnairquality.server.ApiInterface;
import com.hailv.hnairquality.view.adapter.AirQAdapter;
import com.hailv.hnairquality.view.callback.OnItemRecyClickCallback;
import com.hailv.hnairquality.viewmodel.AirQViewModel;
import com.hailv.hnairquality.viewmodel.RecyclerViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    private ApiInterface apiInterface;
    private static ApiClient apiClient;
    AirQViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(AirQViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getObservableProject().observe(this, new Observer<AirQModel>() {
            @Override
            public void onChanged(@Nullable AirQModel airQModel) {
                if (airQModel != null) {
                    viewModel.setAirQViewModel(airQModel);
                    initView();
                    activityMainBinding.setViewmodel(viewModel);
                }
            }
        });
    }

    private final OnItemRecyClickCallback onItemRecyClickCallback = new OnItemRecyClickCallback() {
        @Override
        public void onClickListener(final RecyclerViewModel recyclerViewModel) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                viewModel.setAirQViewModel(addToModel(recyclerViewModel));
                activityMainBinding.setViewmodel(viewModel);
            }
        }
    };

    public AirQModel addToModel(RecyclerViewModel recyclerViewModel) {
        AirQModel airQModel = new AirQModel();
        airQModel.setmCity(recyclerViewModel.nameCityToLV());
        airQModel.setmIndex(recyclerViewModel.airIndexToLV());
        airQModel.setmClassification(recyclerViewModel.classificationToLV());
        airQModel.setMdateTime(recyclerViewModel.dateTimeToLV());
        return airQModel;
    }

    public void initView() {
        AirQAdapter airQAdapter = new AirQAdapter(viewModel.getArrToLv(), onItemRecyClickCallback);
        activityMainBinding.recyclerView.setLayoutManager(
                new LinearLayoutManager(
                        MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        activityMainBinding.recyclerView.setAdapter(airQAdapter);
    }
}