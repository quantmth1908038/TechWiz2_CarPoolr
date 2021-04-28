package com.techwiz2.carpoolr.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techwiz2.carpoolr.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Car> carList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarHolder extends RecyclerView.ViewHolder {

        public CarHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
