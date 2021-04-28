package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.techwiz2.carpoolr.MainActivity;
import com.techwiz2.carpoolr.R;

public class AccountActivity extends AppCompatActivity {

    ImageView ivBack;

    RelativeLayout btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ivBack = findViewById(R.id.ivBack);
        btnLogout = findViewById(R.id.btnLogout);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences setting = getSharedPreferences("CarPoolr", MODE_PRIVATE);
                SharedPreferences.Editor editor = setting.edit();
                editor.putLong("last_login", 1).commit();
                Intent intentOut = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intentOut);
                finish();
            }
        });
    }
}