package com.example.fitnessfirst.ui.home.ui.exercise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * The type Dashboard view model.
 */
public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    /**
     * Instantiates a new Dashboard view model.
     */
    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public LiveData<String> getText() {
        return mText;
    }
}