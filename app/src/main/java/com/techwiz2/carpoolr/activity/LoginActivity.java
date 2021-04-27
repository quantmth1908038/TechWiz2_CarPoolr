package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techwiz2.carpoolr.MainActivity;
import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.connectnetwork.ApiServer;
import com.techwiz2.carpoolr.model.AccessToken;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edEmail,edPass;
    Button btnSubmit,btnSignup;
    ArrayList<Activity> activities =new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activities.add(this);

        init();

        intentMainActivity();
    }

    public void init(){
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPass);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSignup = findViewById(R.id.btnSignup);


        btnSubmit.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
    }

    public void validation() {
        if (edEmail.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter your email",Toast.LENGTH_LONG).show();
            return;
        }
        if (edPass.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void onLogin() {
        validation();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer service = retrofit.create(ApiServer.class);

        service.getLogin(edEmail.getText().toString(), edPass.getText().toString()).enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.body() != null) {
                    AccessToken accessToken = response.body();
                    saveStateLogin(accessToken.getToken());
                    if (checkFirstLogin()) {
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }

    public boolean checkLogin() {
        SharedPreferences setting = getSharedPreferences("CarPoolr", MODE_PRIVATE);
        Long last_login = setting.getLong("last_login", 1619431968);
        Long now = new Date().getTime();
        if ((now - last_login) < 604800) {
            return true;
        }
        return false;
    }

    public void saveStateLogin(String token) {
        SharedPreferences setting = getSharedPreferences("CarPoolr", MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString("access_token", token);
        editor.putBoolean("first_login", true);
        editor.putLong("last_login", new Date().getTime());
        editor.commit();
    }

    public boolean checkFirstLogin() {
        SharedPreferences setting = getSharedPreferences("CarPoolr", MODE_PRIVATE);
        boolean first_login = setting.getBoolean("first_login", true);
        if (first_login){
            setting.edit().putBoolean("first_login", false).commit();
            return true;
        }
        return false;
    }

    public void intentMainActivity() {
        if (checkLogin()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public void goToRegister(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                onLogin();
                break;

            case R.id.btnSignup:
                goToRegister();
                break;
            default:
                break;
        }
    }


}