package com.example.fitnessfirst.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.repository.FirebaseDatabaseHelper;
import com.example.fitnessfirst.repository.User;
import com.example.fitnessfirst.utils.Utils;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtFirstName, edtLastName, edtPassword, edtConfirmPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

    }

    private void init() {
        edtEmail = findViewById(R.id.edt_email);

        edtFirstName = findViewById(R.id.edt_first_name);

        edtLastName = findViewById(R.id.edt_last_name);

        edtPassword = findViewById(R.id.edt_password);

        edtConfirmPassword = findViewById(R.id.edt_confirm_password);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {

            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();
            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();

            if (email.isEmpty()) {
                edtEmail.setError("Email is empty");
                return;
            }
            if (!Utils.validateEmail(email)) {
                edtEmail.setError("Invalid Email");
                return;
            }

            if (firstName.isEmpty()) {
                edtFirstName.setError("FirstName is empty");
                return;
            }

            if (lastName.isEmpty()) {
                edtLastName.setError("LastName is empty");
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Password is empty");
                return;
            }

            if (confirmPassword.isEmpty()) {
                edtConfirmPassword.setError("confirm password is empty");
                return;
            }

            if (password.length() < 6 || confirmPassword.length() < 6) {
                edtPassword.setError("Password length should be more than 6 characters");
                return;
            }

            if (!password.trim().equals(confirmPassword.trim())) {
                Utils.showToast(this, "Passwords doesn't match");
                edtPassword.setText("");
                edtConfirmPassword.setText("");
                return;
            }

            ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
            progressDialog.setTitle("Account creation in progress...");
            progressDialog.show();

            User user = new User(Utils.PUBLIC_USER_TYPE, "", firstName, lastName, email, password);

            FirebaseDatabaseHelper.createUser(user, null);
            FirebaseDatabaseHelper.getAllPublicUsers().add(user);

            new Handler().postDelayed(() -> {
                edtEmail.setText(null);
                edtConfirmPassword.setText(null);
                edtLastName.setText(null);
                edtPassword.setText(null);
                edtFirstName.setText(null);
                progressDialog.dismiss();

                Utils.showToast(getApplicationContext(), "Account created successfully");
            }, 3000);
        }
    }
}