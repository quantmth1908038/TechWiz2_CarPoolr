package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.techwiz2.carpoolr.MainActivity;
import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.adapter.HistoryAdapter;
import com.techwiz2.carpoolr.connectnetwork.ApiServer;
import com.techwiz2.carpoolr.model.History;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryActivity extends AppCompatActivity {

    private List<History> historyList = new ArrayList<>();
    HistoryAdapter adapter;
    ImageView idHome,icBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getData();

        adapter = new HistoryAdapter(this, historyList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);

        RecyclerView rvItem = findViewById(R.id.rvHistory);
        rvItem.setLayoutManager(layoutManager);
        rvItem.setAdapter(adapter);

        idHome = findViewById(R.id.idHome);
        icBack = findViewById(R.id.icBack);

        idHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private  void getData() {
        SharedPreferences setting = getSharedPreferences("CarPoolr", MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer service = retrofit.create(ApiServer.class);
        service.getHistory("Bearer " + setting.getString("access_token", "")).enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                historyList = response.body();
                if (historyList != null) {
                    try {
                        for (History history:historyList) {
                            Geocoder geocoder = new Geocoder(HistoryActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(history.getDestinationX(), history.getDestinationY(), 1);
                            history.setFromAdd(addresses.get(0).getAddressLine(0));
                            List<Address> addresses1 = geocoder.getFromLocation(history.getDepartureX(), history.getDepartureY(), 1);
                            history.setToAdd(addresses1.get(0).getAddressLine(0));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    adapter.reloadData(historyList);
                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
                System.out.println("Failure, retrofitError" + t);
            }
        });
    }



}