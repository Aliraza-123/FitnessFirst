package com.example.fitnessfirst.ui.home.ui.exercise.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.data.local.Repository;
import com.example.fitnessfirst.data.local.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Back exercise activity.
 */
public class BackExerciseActivity extends AppCompatActivity implements Observer<List<Exercise>> {

    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_exercise);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        exerciseAdapter = new ExerciseAdapter(getApplicationContext(), new ArrayList<>());
        recyclerView.setAdapter(exerciseAdapter);

        repository = new Repository(getApplication());
        repository.backExercises().observe(this, this);
    }

    @Override
    public void onChanged(List<Exercise> exercises) {
        exerciseAdapter.setAdapterData(exercises);
    }
}