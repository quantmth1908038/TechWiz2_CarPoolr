package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.connectnetwork.ApiServer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookCarActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    private TextView tvFromAdd, tvToAdd, tvDate;
    private Spinner spinnerSlot;
    private Button btnConfirm;

    private int mDate,mMonth,mYear;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_car);

        intent = getIntent();
        init();

    }

    public void init() {
        tvFromAdd = findViewById(R.id.tvFromAdd);
        tvToAdd = findViewById(R.id.tvToAdd);
        tvDate = findViewById(R.id.tvDate);
        btnConfirm = findViewById(R.id.btnConfirmBookCar);

        c = Calendar.getInstance();
        tvDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime()));

        btnConfirm.setOnClickListener(this);
        tvDate.setOnClickListener(this);

        tvFromAdd.setText(intent.getStringExtra("fromAddress"));
        tvToAdd.setText(intent.getStringExtra("toAddress"));

        String[] genders = {"1", "2", "3","4", "5", "6","7", "8", "9","10", "11", "12","13", "14", "15","16", "17", "18","19", "20"};
        spinnerSlot = findViewById(R.id.spinnerSlot);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSlot.setAdapter(adapter);
    }

    public void setDate() {
        final Calendar Cal = Calendar.getInstance();

        mDate = Cal.get(Calendar.DATE);
        mMonth = Cal.get(Calendar.MONTH);
        mYear = Cal.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(BookCarActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override

            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c = Calendar.getInstance();
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                Calendar check = Calendar.getInstance();
                if (c.getTimeInMillis() > check.getTimeInMillis()) {
                    String current = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
                    tvDate.setText(current);
                } else {
                    c = check;
                    String current = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
                    tvDate.setText(current);
                    Toast.makeText(BookCarActivity.this,"Time fail",Toast.LENGTH_LONG).show();
                }
            }
        }, mYear,mMonth,mDate);
        datePickerDialog.show();
    }

    public void bookCar() {
        SharedPreferences setting = getSharedPreferences("CarPoolr", MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer service = retrofit.create(ApiServer.class);
        service.bookCar("Bearer " + setting.getString("access_token", ""), intent.getDoubleExtra("fromLati", 0),
                intent.getDoubleExtra("fromLongi", 0),
                intent.getDoubleExtra("toLati", 0),
                intent.getDoubleExtra("toLongi", 0),
                Integer.valueOf(spinnerSlot.getSelectedItem().toString()),
                c.getTimeInMillis())
                .enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() == 1) {
                            Intent intent = new Intent(BookCarActivity.this, HistoryActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.d("TAG", "onFailure: ");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDate:
                setDate();
                break;
            case R.id.btnConfirmBookCar:
                bookCar();
                break;
        }
    }
}