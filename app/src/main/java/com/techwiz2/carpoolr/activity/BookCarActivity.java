package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.techwiz2.carpoolr.R;

public class BookCarActivity extends AppCompatActivity {

    private Intent intent;

    private TextView tvFromAdd, tvToAdd;

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

        tvFromAdd.setText(intent.getStringExtra("fromAddress"));
        tvToAdd.setText(intent.getStringExtra("toAddress"));
    }



}