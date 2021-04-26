package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edEmail, edPass, edPassConfirm;
    Button btSignup, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

    }

    public void init() {
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPass);
        edPassConfirm = findViewById(R.id.edPassConfirm);

        btSignup = findViewById(R.id.btSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

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
        if (edPassConfirm.getText().toString().isEmpty()){
            Toast.makeText(this,"You must confirm password",Toast.LENGTH_LONG).show();
            return;
        }

        String password = edPass.getText().toString();
        String comfirmPassword = edPassConfirm.getText().toString();


        if (password != comfirmPassword){
            Toast.makeText(this,"Password not match",Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void onRegister() {
        validation();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer service = retrofit.create(ApiServer.class);

        service.getRegister(edEmail.getText().toString(), edPass.getText().toString()).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    onLogin();
                } else {
                    Toast.makeText(RegisterActivity.this,"Email already exits",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });

    }

    public void onLogin() {
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
                    Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });

    }

    private void saveStateLogin(String token){
        SharedPreferences setting = getSharedPreferences("CarPoolr", MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString("access_token", token);
        editor.putBoolean("first_login", false);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSignup:
                onRegister();
                break;
            default:
                break;
        }
    }
}