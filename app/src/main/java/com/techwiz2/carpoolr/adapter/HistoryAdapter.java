package com.techwiz2.carpoolr.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techwiz2.carpoolr.model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<History> historyList;

    public HistoryAdapter(Activity activity, List<History> historyList) {
        this.activity = activity;
        this.historyList = historyList;
    }

    public void reloadData(List<History> list) {
        this.historyList = list;
        notifyDataSetChanged();
    }

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
        return historyList.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTitle, tvContent;
        ImageView ivCover;

        RelativeLayout r;


        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
