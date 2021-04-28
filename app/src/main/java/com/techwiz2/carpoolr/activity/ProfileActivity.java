package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.connectnetwork.ApiServer;
import com.techwiz2.carpoolr.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    private void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiServer.SERVER_URL)
                            .addConverterFactory(GsonConverterFactory
                            .create()).build();
        ApiServer service = retrofit.create(ApiServer.class);
        service.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body() == null){
                    return;
                }
                User model = response.body();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("ProfileActivity","onFailure:" + t);
            }
        });
    }

}