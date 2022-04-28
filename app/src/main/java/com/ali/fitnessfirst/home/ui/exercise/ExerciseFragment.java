package com.ali.fitnessfirst.home.ui.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ali.fitnessfirst.databinding.FragmentDashboardBinding;
import com.ali.fitnessfirst.home.ui.exercise.activities.ArmsExerciseActivity;
import com.ali.fitnessfirst.home.ui.exercise.activities.BackExerciseActivity;
import com.ali.fitnessfirst.home.ui.exercise.activities.ChestExerciseActivity;
import com.ali.fitnessfirst.home.ui.exercise.activities.CoreExerciseActivity;
import com.ali.fitnessfirst.home.ui.exercise.activities.LegsExerciseActivity;
import com.ali.fitnessfirst.home.ui.exercise.activities.ShouldersExerciseActivity;


public class ExerciseFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        binding.crdArms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getContext(), ArmsExerciseActivity.class);
                startActivity(intent);
            }
        });
        binding.crdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getContext(), BackExerciseActivity.class);
                startActivity(intent);
            }
        });
        binding.crdChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getContext(), ChestExerciseActivity.class);
                startActivity(intent);
            }
        });
        binding.crdCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getContext(), CoreExerciseActivity.class);
                startActivity(intent);
            }
        });
        binding.crdLegs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getContext(), LegsExerciseActivity.class);
                startActivity(intent);
            }
        });
        binding.crdShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getContext(), ShouldersExerciseActivity.class);
                startActivity(intent);
            }
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