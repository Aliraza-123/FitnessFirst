package com.example.fitnessfirst;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessfirst.home.HomeActivity;
import com.example.fitnessfirst.login.LoginActivity;
import com.example.fitnessfirst.repository.CurrentDatabase;
import com.example.fitnessfirst.repository.FirebaseDatabaseHelper;
import com.example.fitnessfirst.utils.NetworkStateReceiver;
import com.example.fitnessfirst.utils.Utils;
import com.google.android.material.snackbar.Snackbar;


/**
 * The type Splash activity.
 */
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    private static final String TAG = "SplashActivity";
    private Snackbar snackbar;
    private NetworkStateReceiver networkStateReceiver;

    private boolean opened = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View parentLayout = findViewById(android.R.id.content);
        snackbar = Snackbar.make(parentLayout, "Not connected to Internet! Please turn on wifi or mobile data",
                Snackbar.LENGTH_INDEFINITE);

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);

        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (networkStateReceiver != null)
            unregisterReceiver(networkStateReceiver);
    }

    private void splashScreenTime() {

        Log.e(TAG, "In Splash Screen");
        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */
        new Handler().postDelayed(() -> {

            String isLogin = Utils.getDataFromSharedPrefs(getApplicationContext(), "is_login");

            if (!TextUtils.isEmpty(isLogin)) {
                findViewById(R.id.btn_started).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_started).setOnClickListener(view
                        -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));
            } else {
                String email = Utils.getDataFromSharedPrefs(getApplicationContext(), "email");
                CurrentDatabase.setCurrentPublicUser(FirebaseDatabaseHelper.getUserByEmail(email));
                startActivity(new Intent(this, HomeActivity.class));
            }
            finish();

        }, 4000);
    }

    @Override
    public void networkAvailable() {
        snackbar.dismiss();
        if (opened) {
            new FirebaseDatabaseHelper();
            splashScreenTime();
            opened = false;
        }
    }

    @Override
    public void networkUnavailable() {
        snackbar.show();
    }
}
