package com.example.fitnessfirst.ui;

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

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.data.local.Repository;
import com.example.fitnessfirst.data.local.entities.ExerciseEssentials;
import com.example.fitnessfirst.data.remote.CurrentDatabase;
import com.example.fitnessfirst.data.remote.FirebaseDatabaseHelper;
import com.example.fitnessfirst.data.remote.models.User;
import com.example.fitnessfirst.ui.home.HomeActivity;
import com.example.fitnessfirst.ui.login.GetStartedActivity;
import com.example.fitnessfirst.ui.login.LoginActivity;
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

        firstDbSetup();

        new Handler().postDelayed(() -> {

            String isLogin = Utils.getDataFromSharedPrefs(getApplicationContext(), "is_login");

            if (TextUtils.isEmpty(isLogin)) {
                startActivity(new Intent(getApplicationContext(), GetStartedActivity.class));
            } else {
                String email = Utils.getDataFromSharedPrefs(getApplicationContext(), "email");
                User user = FirebaseDatabaseHelper.getUserByEmail(email);
                if (user != null) {
                    CurrentDatabase.setCurrentPublicUser(FirebaseDatabaseHelper.getUserByEmail(email));
                    startActivity(new Intent(this, HomeActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
            finish();

        }, 3000);
    }

    private void firstDbSetup() {

        String isFirstLaunch = Utils.getDataFromSharedPrefs(getApplicationContext(), "FIRST");
        if (isFirstLaunch.isEmpty()) {
            Repository repository = new Repository(getApplication());
            repository.insertExEssentials(new ExerciseEssentials("Sneakers", "Please don't assume that any old footwear will be efficient for the gym. Please ensure that you purchase/have trainers that are fit for your workouts and fit your feet well. Ensuring they have a combination of support, cushioning and grip."));
            repository.insertExEssentials(new ExerciseEssentials("Headphones", "Headphones are an integral gym essential, get headphones and listen to music that will motivate you. Music can also be utilised to help with your focus and eliminate distractions, which could help you to set a potential personal best or to grind out a routine you find tough."));
            repository.insertExEssentials(new ExerciseEssentials("Reusable Water Bottle", "Staying hydrated is very essential during workouts, using reusable bottles is not only better for the environment and it can also keep your water colder for longer periods of time."));
            repository.insertExEssentials(new ExerciseEssentials("Gym Clothes", "Gym clothes are essential because they can help prevent injury, for example tighter clothing can restrict movement and increases the risks of injuries. In addition, gym clothing can help with preventing overheating as there are many gym clothes which help with sweat. Finally, gym clothes gives you the confidence in the gym. (Looking your best, makes you perform your best)."));
            repository.insertExEssentials(new ExerciseEssentials("Dry Shampoo", "Dry Shampoo is quite useful because it can reduce your after exercise changing room time, as it provides a waterless method to refresh your hair. It can be used to absorb sweat and excess oil in hair as well as to repel any odours that come from a gym workout."));
            repository.insertExEssentials(new ExerciseEssentials("Shower Essentials", "Shower essentials could include shower gel, body cream, towel and shampoo. These products are helpful in order to keep clean and fresh post exercise."));
            repository.insertExEssentials(new ExerciseEssentials("Deoderant ", "Deoderant can help you stay fresh or reduce odours pre/post gym routine. "));
            repository.insertExEssentials(new ExerciseEssentials("Padlocks", "Padlocks are used in order to keep your things safe within gym lockers. Keep a gym padlock and the code somewhere safe."));
            repository.insertExEssentials(new ExerciseEssentials("Weight Lifting Gloves", "Weight Lifting Gloves will help you because it can protect your fingers from stress and can possibly reduce amount of calluses, it can also help you with gripping the weight better."));
            repository.insertExEssentials(new ExerciseEssentials("Weight Lifting Belt", "Weightlifting belts are an essential to gym-going because it can help reduce stress on the lower back while you are lifting in an upright position, these exercises could be deadlifting, squatting etc. "));


            Utils.saveDataInSharedPrefs(getApplicationContext(), "FIRST", "true");
        }
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
