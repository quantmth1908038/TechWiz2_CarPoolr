package com.techwiz2.carpoolr.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techwiz2.carpoolr.OnClickListener.ItemOnClickListener;
import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.model.History;

import java.text.DateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<History> historyList;
    private ItemOnClickListener itemOnClickListener;

    public HistoryAdapter(Activity activity, List<History> historyList) {
        this.activity = activity;
        this.historyList = historyList;
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    public void reloadData(List<History> list) {
        this.historyList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_history, parent, false);
        HistoryHolder holder = new HistoryHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HistoryHolder historyHolder = (HistoryHolder) holder;
        History model = historyList.get(position);
        historyHolder.tvTitleTime.setText(DateFormat.getDateInstance(DateFormat.FULL).format(model.getTime()));
        historyHolder.ePointAway.setText(model.getFromAdd());
        historyHolder.eDestination.setText(model.getToAdd());
        historyHolder.tvFare.setText(String.valueOf(model.getFare()) + " USD");
        if (model.getCar() != null) {
            historyHolder.tvNameCar.setText(model.getCar().getName());
            historyHolder.tvPlate.setText(model.getCar().getPlate());
        }
        if (model.getStatus().equals("1")) {
            historyHolder.tvStatus.setText("Wait State");
        } else if (model.getStatus().equals("2")) {
            historyHolder.tvStatus.setText("Success");
        } else if (model.getStatus().equals("3")) {
            historyHolder.tvStatus.setText("Cancel");
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView tvTitleTime, ePointAway, eDestination, tvFare, tvNameCar, tvPlate, tvStatus;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleTime = itemView.findViewById(R.id.tvTilteTime);
            ePointAway = itemView.findViewById(R.id.ePointAway);
            eDestination = itemView.findViewById(R.id.eDestination);
            tvFare = itemView.findViewById(R.id.tvFare);
            tvNameCar = itemView.findViewById(R.id.tvNameCar);
            tvPlate = itemView.findViewById(R.id.tvPlate);
            tvStatus = itemView.findViewById(R.id.tvStatus);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    History model = historyList.get(getAdapterPosition());
//                    itemOnClickListener.onClickListenerHistory(model);
//                }
//            });

        }
    }

}
