package com.example.fitnessfirst.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.txt_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_fpass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
//        setupToolbar();

    }

    private void setupToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}