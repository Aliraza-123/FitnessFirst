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
import com.example.fitnessfirst.data.local.entities.Exercise;
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

            repository.insertExercise(new Exercise("Chest", "Seated Chest Press", "Keep your wrists in line with your forearms in a neutral wrist position. Exhale and extend your arms fully (don't lock your elbows). During this movement, keep your neck still and your head steady against the back support. When you reach full extension, you should feel resistance against the horizontal push. During this recovery, bend your elbows and return to the starting position, breathing in."));
            repository.insertExercise(new Exercise("Back", "Lat Pulldowns", "Position yourself seated on the lat pulldown machine and attach a wide grip handle. The handle should be held pronated (double overhand) and you should begin by depressing your shoulders and flexing your elbow while extending your shoulder. Slowly lower the handle back to the starting position under control by pulling the handle toward your torso until the elbows are in line with your torso. Repeat this movement as many times as you wish."));
            repository.insertExercise(new Exercise("Arms", "Barbell Curl", "Bend your elbow and curl the weight forward. Keep your upper arms still during this exercise. Move the barbell until the biceps are fully contracted and the bar is at shoulder height. Squeeze your biceps for a moment after holding this position. Return the barbell to the starting position. Repeat this movement as many times as you like.um. Now, drive your hips forwards and straighten your back so you send the kettlebell up to your shoulder height. Finally, let the kettlebell return back between your legs and repeat this."));
            repository.insertExercise(new Exercise("Core", "Machine Crunch", "Select a resistance setting and sit on the ab machine with your feet under the pads and your hands on the top grips. Rest your triceps on the pads available while your arms remain bent at a 90-degree angle. Start to pull your knees up while clenching your abdomen simultaneously. As you complete this exercise, exhale. Ensure you're moving slowly and steadily. Try to focus on moving the weight using your core while relaxing your legs and feet. Slowly return to the starting posture when you breathe in after a second break. Repeat the movement for the appropriate number of repetitions."));
            repository.insertExercise(new Exercise("Legs", "Squats", "Shoulder width apart stand up with your feet. Now, bend your knees, and press your hips backwards and stop your movement once the hips are slightly lower than your knees. Press your heels to the floor and then return to your initial position meaning standing position and repeat until your set is completed."));
            repository.insertExercise(new Exercise("Shoulders", "Diamond Pushups", "Your hands should be slightly narrower than your shoulders.  Grip the ground with your hands creating a diamond shape, and rotate your shoulders outward to engage your lats. Straighten your legs to lift your knees off the ground so you end up in a push-up position.  Your legs should be hip-width apart or together. While maintaining your alignment, initiate the upward movement by squeezing your chest and straightening your elbows.  Your shoulder blades should protract as you push to the top of the movement. "));


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
