package com.example.fitnessfirst.login;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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