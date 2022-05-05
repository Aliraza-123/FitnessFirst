package com.example.fitnessfirst.ui.home.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.data.local.Repository;
import com.example.fitnessfirst.data.local.entities.TodoTask;

import java.util.ArrayList;
import java.util.List;


public class TodoFragment extends Fragment implements View.OnClickListener,
        Observer<List<TodoTask>>, TodoAdapter.OnDeleteClickListener, TodoAdapter.OnCheckedClickListener {

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;

    private Repository repository;

    private EditText inputTextEditText;
    private Button submitButton;

    public TodoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        todoAdapter = new TodoAdapter(getContext(), new ArrayList<>());
        todoAdapter.setOnDeleteClickListener(this);
        todoAdapter.setOnCheckedClickListener(this);
        recyclerView.setAdapter(todoAdapter);

        repository = new Repository(getActivity().getApplication());
        repository.getAllTodoTasks().observe(getViewLifecycleOwner(), this);

        inputTextEditText = view.findViewById(R.id.et_input_task);
        submitButton = view.findViewById(R.id.btn_submit);

        submitButton.setOnClickListener(this);
        return view;

    }

    @Override
    public void onChanged(List<TodoTask> todoTasks) {
        todoAdapter.setAdapterData(todoTasks);

    }

    @Override
    public void onClick(View view) {
        if (view == submitButton) {
            String text = inputTextEditText.getText().toString().trim();

            if (!text.isEmpty()) {
                TodoTask todoTask = new TodoTask(text);
                repository.insertTodoTask(todoTask);
                inputTextEditText.setText("");
            }
        }
    }


    @Override
    public void onDeleteClicked(TodoTask todoTask) {
        repository.deleteTodoTask(todoTask);

    }

    @Override
    public void onCheckClicked(TodoTask todoTask) {
        if (todoTask.getIsCompleted() == 0)
            todoTask.setIsCompleted(1);
        else todoTask.setIsCompleted(0);
        repository.updateTodoTask(todoTask);
    }
}