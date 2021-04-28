package com.techwiz2.carpoolr.activity;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.model.Ride;

import java.util.List;

public class RidesAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Ride> list;
    public RidesAdapter(Activity activity,List<Ride>list){
        this.activity= activity;
        this.list = list;
    }
    public void reloadData(List<Ride> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_ride_activity,parent,false);
        RidesHolder holder = new RidesHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RidesHolder hd = (RidesHolder) holder;
        Ride model = list.get(position);
        hd.tvSlot.setText(model.getSlot());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class RidesHolder extends RecyclerView.ViewHolder{
        TextView tvDes, tvDep,tVtime,tvSlot;
        public RidesHolder(@NonNull View itemView) {
            super(itemView);
            tvDep = itemView.findViewById(R.id.tvDep);
            tvDes = itemView.findViewById(R.id.tvDes);
            tVtime = itemView.findViewById(R.id.tVtime);
            tvSlot = itemView.findViewById(R.id.tVslot);
        }

    }

}
