package com.example.fitnessfirst.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitnessfirst.R;


public class SignUpActivity extends AppCompatActivity {

    EditText edtEmail, edtFirstName, edtLastName, edtPassword, edtConfirmPassword;
    Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation()){
                    Toast.makeText(SignUpActivity.this, "validation true", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    private void init(){
        edtEmail =  findViewById(R.id.edt_email);
        edtFirstName = findViewById(R.id.edt_first_name);
        edtLastName =  findViewById(R.id.edt_last_name);
        edtPassword =  findViewById(R.id.edt_password);
        edtConfirmPassword =  findViewById(R.id.edt_confirm_password);
        btnLogin = findViewById(R.id.btn_login);
    }
    private boolean validation(){
        if(edtEmail.getText().toString().isEmpty()){
            edtEmail.setError("email is empty");
            return false;
        }
        if(edtFirstName.getText().toString().isEmpty()){
            edtFirstName.setError("firstName is empty");
            return false;
        }
        if(edtLastName.getText().toString().isEmpty()){
            edtLastName.setError("lastName is empty");
            return false;
        }
        if(edtPassword.getText().toString().isEmpty()){
            edtPassword.setError("password is empty");
            return false;
        }
        if(edtConfirmPassword.getText().toString().isEmpty()){
            edtConfirmPassword.setError("confirm password is empty");
            return false;
        }
        if(!edtPassword.getText().toString().trim().equalsIgnoreCase(edtConfirmPassword.getText().toString().trim())){
            Toast.makeText(this, "Password and Confirm password is not matched", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}