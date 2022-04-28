package com.example.fitnessfirst.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.repository.FirebaseDatabaseHelper;
import com.example.fitnessfirst.repository.User;
import com.example.fitnessfirst.utils.Utils;


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
                    User user =  new User();
                    user.setEmailAddress(edtEmail.getText().toString().trim());
                    user.setFirstName(edtFirstName.getText().toString().trim());
                    user.setLastName(edtLastName.getText().toString().trim());
                    user.setPassword(edtPassword.getText().toString().trim());
                    if( FirebaseDatabaseHelper.createUser(user,null)){
                        Intent intent =  new Intent(SignUpActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Some issue!!", Toast.LENGTH_SHORT).show();

                    }

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
        if(!Utils.validateEmail(edtEmail.getText().toString())){
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
        if(edtPassword.getText().toString().isEmpty()||edtPassword.getText().toString().length()<6){
            edtPassword.setError("password is empty");
            return false;
        }
        if( (Utils.validatePassword(edtPassword.getText().toString()))){
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