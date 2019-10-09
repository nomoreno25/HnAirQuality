package com.hailv.hnairquality.adapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.hailv.hnairquality.databinding.ListItemBinding;
import com.hailv.hnairquality.viewmodel.AirQViewModel;

import java.util.List;

public class AirQAdapter extends RecyclerView.Adapter<AirQAdapter.ViewHolder> {
    private List<AirQViewModel> mData;
    private LayoutInflater mInflater;
    public static int index = 0;

    public AirQAdapter(List<AirQViewModel> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        final ListItemBinding listItemBinding = ListItemBinding.inflate(mInflater, parent, false);
        return new ViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AirQViewModel pollutionInfoViewModel = mData.get(i);
        viewHolder.bind(pollutionInfoViewModel);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemBinding itemBinding;

        public ViewHolder(ListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(AirQViewModel airQViewModel) {
            this.itemBinding.setViewmodel(airQViewModel);
        }

        public ListItemBinding getItemBinding() {
            return itemBinding;
        }
    }
}