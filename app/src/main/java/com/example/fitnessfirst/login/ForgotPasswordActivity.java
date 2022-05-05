package com.example.fitnessfirst.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.databinding.ActivityForgotPasswordBinding;
import com.example.fitnessfirst.utils.Utils;


public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        binding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation()){
//                    if(FirebaseDatabaseHelper.resetPassword(binding.edtEmail.getText().toString().trim())){
//                        Toast.makeText(ForgotPasswordActivity.this, "Link is sent to your email", Toast.LENGTH_SHORT).show();
//                        Intent intent =  new Intent(ForgotPasswordActivity.this,LoginActivity.class);
//                        startActivity(intent);
//
//                    }


                }

            }
        });
        binding.txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        setContentView(binding.getRoot());



    }

    private boolean validation(){
        if(binding.edtEmail.getText().toString().isEmpty()){
            binding.edtEmail.setError("email is empty");
            return false;
        }
        if(!Utils.validateEmail(binding.edtEmail.getText().toString())){
            binding.edtEmail.setError("email is not correct");
            return false;
        }


        return true;
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