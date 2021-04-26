package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.connectnetwork.ApiServer;
import com.techwiz2.carpoolr.model.Response;

import retrofit2.Call;
import retrofit2.Callback;
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

    public void onRegister() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer service = retrofit.create(ApiServer.class);

        service.getRegister(edEmail.getText().toString(), edPass.getText().toString()).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                String status = response.body().getStatus();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("TAG", "onFailure: sasd");
            }
        });

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