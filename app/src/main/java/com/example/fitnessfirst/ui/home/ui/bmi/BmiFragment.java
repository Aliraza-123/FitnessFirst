package com.example.fitnessfirst.ui.home.ui.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitnessfirst.databinding.FragmentBmiBinding;
import com.example.fitnessfirst.utils.Utils;


public class BmiFragment extends Fragment {

    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.hSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.tvHeight.setText("" + progress);
                calculateBmi(Double.parseDouble(binding.tvHeight.getText().toString().trim()),
                        Double.parseDouble(binding.tvWeight.getText().toString().trim()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.wSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.tvWeight.setText("" + i);
                calculateBmi(Double.parseDouble(binding.tvHeight.getText().toString().trim()),
                        Double.parseDouble(binding.tvWeight.getText().toString().trim()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return root;
    }

    private void calculateBmi(Double height, Double weight) {
        height = height / 100;
        double bmi = Utils.round(weight / (height * height));
        binding.tvResult.setText("Result:" + bmi);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}