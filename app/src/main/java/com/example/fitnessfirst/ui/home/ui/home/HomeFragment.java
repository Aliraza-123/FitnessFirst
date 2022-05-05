package com.example.fitnessfirst.ui.home.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.data.local.Repository;
import com.example.fitnessfirst.data.local.entities.ExerciseEssentials;
import com.example.fitnessfirst.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements Observer<List<ExerciseEssentials>> {

    private FragmentHomeBinding binding;

    private RecyclerView recyclerView;
    private ExEssentialAdapter exEssentialAdapter;

    private Repository repository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        exEssentialAdapter = new ExEssentialAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(exEssentialAdapter);

        repository = new Repository(getActivity().getApplication());
        repository.getAllExEssentials().observe(getViewLifecycleOwner(), this);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onChanged(List<ExerciseEssentials> exerciseEssentials) {
        exEssentialAdapter.setAdapterData(exerciseEssentials);

    }
}