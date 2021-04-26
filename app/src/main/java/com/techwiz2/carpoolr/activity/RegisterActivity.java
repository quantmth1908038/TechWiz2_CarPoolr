package com.techwiz2.carpoolr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.techwiz2.carpoolr.R;

public class RegisterActivity extends AppCompatActivity {

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

    }

}