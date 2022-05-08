package com.example.fitnessfirst.ui.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.databinding.ActivityHomeBinding;
import com.example.fitnessfirst.ui.home.ui.bmi.BmiFragment;
import com.example.fitnessfirst.ui.home.ui.exercise.ExerciseFragment;
import com.example.fitnessfirst.ui.home.ui.home.HomeFragment;
import com.example.fitnessfirst.ui.home.ui.todo.TodoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * The type Home activity.
 */
public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityHomeBinding binding;

    private HomeFragment homeFragment;
    private ExerciseFragment exerciseFragment;
    private BmiFragment bmiFragment;
    private TodoFragment todoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        homeFragment = new HomeFragment();
        exerciseFragment = new ExerciseFragment();
        bmiFragment = new BmiFragment();
        todoFragment = new TodoFragment();

        loadFragment(homeFragment);

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = homeFragment;
                break;

            case R.id.navigation_exercise:
                fragment = exerciseFragment;
                break;

            case R.id.navigation_bmi:
                fragment = bmiFragment;
                break;

            case R.id.navigation_todo:
                fragment = todoFragment;
                break;

        }
        return loadFragment(fragment);
    }

    /**
     * Loads the fragment
     *
     * @param fragment the Fragment
     * @return returns true if the fragment is loaded successfully.
     */
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_home, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}