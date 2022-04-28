package com.example.fitnessfirst.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.fitnessfirst.R;
import com.example.fitnessfirst.databinding.ActivityForgotPasswordBinding;
import com.example.fitnessfirst.databinding.ActivityLoginBinding;
import com.example.fitnessfirst.home.HomeActivity;
import com.example.fitnessfirst.repository.FirebaseDatabaseHelper;
import com.example.fitnessfirst.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                if(validation()){
                    FirebaseDatabaseHelper.loginUser(binding.edtEmail.getText().toString().trim(),
                            binding.edtPassword.getText().toString().trim(),LoginActivity.this);

                }


            }
        });
        findViewById(R.id.btn_fpass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean validation(){
        if(binding.edtEmail.getText().toString().isEmpty()){
            binding.edtEmail.setError("email is empty");
            return false;
        }
        if(!Utils.validateEmail(binding.edtEmail.getText().toString())){
            return false;
        }

        if(binding.edtPassword.getText().toString().isEmpty()||binding.edtPassword.getText().toString().length()<6){
            binding.edtPassword.setError("password is empty");
            return false;
        }
//        if( (Utils.validatePassword(binding.edtPassword.getText().toString().trim()))){
//            return false;
//        }

        return true;
    }

}