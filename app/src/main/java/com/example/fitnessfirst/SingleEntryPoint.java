package com.example.fitnessfirst;

import android.app.Application;
import android.content.Context;

import com.example.fitnessfirst.repository.FirebaseDatabaseHelper;
import com.google.firebase.FirebaseApp;


public class SingleEntryPoint extends Application {

    private static final String TAG = "ApplicationContext";

    private static Context context;

    public static Context getAppContext() {
        return SingleEntryPoint.context;
    }

    public void onCreate() {
        super.onCreate();
        SingleEntryPoint.context = getApplicationContext();
        FirebaseApp.initializeApp(getApplicationContext());
        new FirebaseDatabaseHelper();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}