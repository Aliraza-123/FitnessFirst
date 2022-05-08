package com.example.fitnessfirst.ui.home.ui.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnessfirst.databinding.FragmentDashboardBinding;
import com.example.fitnessfirst.ui.home.ui.exercise.activities.ArmsExerciseActivity;
import com.example.fitnessfirst.ui.home.ui.exercise.activities.BackExerciseActivity;
import com.example.fitnessfirst.ui.home.ui.exercise.activities.ChestExerciseActivity;
import com.example.fitnessfirst.ui.home.ui.exercise.activities.CoreExerciseActivity;
import com.example.fitnessfirst.ui.home.ui.exercise.activities.LegsExerciseActivity;
import com.example.fitnessfirst.ui.home.ui.exercise.activities.ShouldersExerciseActivity;


/**
 * The type Exercise fragment.
 */
public class ExerciseFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        binding.crdArms.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ArmsExerciseActivity.class);
            startActivity(intent);
        });
        binding.crdBack.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), BackExerciseActivity.class);
            startActivity(intent);
        });
        binding.crdChest.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ChestExerciseActivity.class);
            startActivity(intent);
        });
        binding.crdCore.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CoreExerciseActivity.class);
            startActivity(intent);
        });
        binding.crdLegs.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), LegsExerciseActivity.class);
            startActivity(intent);
        });
        binding.crdShoulder.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ShouldersExerciseActivity.class);
            startActivity(intent);
        });
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}