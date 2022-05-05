package com.example.fitnessfirst.ui.login;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.data.remote.CurrentDatabase;
import com.example.fitnessfirst.data.remote.FirebaseDatabaseHelper;
import com.example.fitnessfirst.ui.home.HomeActivity;
import com.example.fitnessfirst.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

/**
 * The type Login activity.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private boolean doubleBackToExitPressedOnce = false;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressDialog progressDialog;

    private TextView forgotPasswordTextView;
    private TextView createAccountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeWidgets();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeWidgets() {
        emailEditText = findViewById(R.id.edt_email);
        passwordEditText = findViewById(R.id.edt_password);

        loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this); // setting click listener to the imageview
        loginButton.setOnTouchListener((view, motionEvent) -> {
            Animation click = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.click);
            loginButton.startAnimation(click);
            return false;
        });

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Loading...");


        forgotPasswordTextView = findViewById(R.id.btn_fpass);
        createAccountTextView = findViewById(R.id.txt_signup);

        forgotPasswordTextView.setOnClickListener(this);
        createAccountTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        /* If next Button is clicked this block of code will execute */
        if (view == loginButton) {

            String email = emailEditText.getText().toString().trim(); // getting string inside the edit text and removing whitespaces
            String password = passwordEditText.getText().toString().trim(); // same as above for password

            /* Checks if the fields are empty or not, and prompt the user accordingly */
            if (Utils.validateEmail(email)
                    && Utils.validatePassword(password)) {
                loginUser(email, password);
            } else {
                if (!Utils.validateEmail(email))
                    emailEditText.setError("Invalid Email");
                else if (!Utils.validatePassword(password))
                    passwordEditText.setError("Invalid password, password should be atleast 7 characters long");
            }
        }

        if (view == forgotPasswordTextView) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("Send reset email");

            LayoutInflater inflater = (LayoutInflater) LoginActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams") View alertDialogView = inflater.inflate(R.layout.input_email, null);
            builder.setView(alertDialogView);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setCancelable(false);

            final EditText inputEditText = alertDialogView.findViewById(R.id.inputEditText);

            builder.setView(alertDialogView);

            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                String dialogEmailAddress = inputEditText.getText().toString();
                dialog.cancel();
                if (Utils.validateEmail(dialogEmailAddress)) {
                    Utils.showToast(getApplicationContext(), "Password reset email sent to: " + dialogEmailAddress);
                    FirebaseAuth.getInstance().sendPasswordResetEmail(dialogEmailAddress);
                } else
                    Utils.showToast(getApplicationContext(), "Incorrect email");
            });
            builder.show();
        }

        if (view == createAccountTextView) {
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.putExtra("TYPE", false);
            startActivity(intent);
        }
    }

    private void loginUser(final String email, final String password) {
        progressDialog.show();

        Intent intent;

        if (FirebaseDatabaseHelper.getUserByEmail(email) != null) {
            intent = new Intent(LoginActivity.this, HomeActivity.class);
            CurrentDatabase.setCurrentPublicUser(FirebaseDatabaseHelper.getUserByEmail(email));
            Log.d(TAG, "setting current user as: " + CurrentDatabase.getCurrentPublicUser().toString());

        } else {
            progressDialog.dismiss();
            Utils.showToast(getApplicationContext(), "No user found");
            return;
        }

        Intent finalIntent = intent;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.e(TAG, "signInWithEmail:success");
                        progressDialog.dismiss();
                        Utils.showToast(getApplicationContext(), "Login Successful!");
                        startActivity(finalIntent);
                        finish();

                        Utils.saveDataInSharedPrefs(getApplicationContext(), "email", email);
                        Utils.saveDataInSharedPrefs(getApplicationContext(), "password", password);

                        Utils.saveDataInSharedPrefs(getApplicationContext(), "is_login", "USER");

                    } else {
                        progressDialog.dismiss();
                        Utils.showToast(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage());
                    }
                });

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Utils.showToast(this, "Click BACK twice to exit");

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
