package com.hailv.hnairquality.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hailv.hnairquality.databinding.ListItemBinding;
import com.hailv.hnairquality.view.callback.OnItemRecyClickCallback;
import com.hailv.hnairquality.viewmodel.RecyclerViewModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AirQAdapter extends RecyclerView.Adapter<AirQAdapter.CustomView> {
    List<RecyclerViewModel> newsList;
    private LayoutInflater layoutInflater;
    OnItemRecyClickCallback onItemRecyClickCallback;

    public AirQAdapter(List<RecyclerViewModel> newsList, OnItemRecyClickCallback onItemRecyClickCallback) {
        this.newsList = newsList;
        this.onItemRecyClickCallback = onItemRecyClickCallback;
    }

    @Override
    public CustomView onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        final ListItemBinding newsBinding = ListItemBinding.inflate(layoutInflater, parent, false);

        newsBinding.setOnClickItem(onItemRecyClickCallback);
        return new CustomView(newsBinding);
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {
        RecyclerViewModel recyclerViewModel = newsList.get(position);
        holder.bind(recyclerViewModel);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class CustomView extends RecyclerView.ViewHolder {
        private ListItemBinding listItemBinding;

        public CustomView(ListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

        public void bind(RecyclerViewModel recyclerViewModel) {
            this.listItemBinding.setViewmodel(recyclerViewModel);
        }

        public ListItemBinding getNewsBinding() {
            return listItemBinding;
        }
    }
}
