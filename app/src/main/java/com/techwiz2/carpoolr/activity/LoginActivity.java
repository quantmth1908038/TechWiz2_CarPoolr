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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edEmail,edPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    public void init(){
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPass);

        btnLogin = findViewById(R.id.btSignup);

        btnLogin.setOnClickListener(this);
    }

    public void onLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer service = retrofit.create(ApiServer.class);

        service.getLogin(edEmail.getText().toString(), edPass.getText().toString()).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                boolean status = response.body().getStatus();
                if (status) {

                }
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
                onLogin();
                break;
            default:
                break;
        }
    }


}