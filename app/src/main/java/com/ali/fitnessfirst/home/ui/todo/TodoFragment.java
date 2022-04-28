package com.ali.fitnessfirst.home.ui.todo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.fitnessfirst.R;
import com.ali.fitnessfirst.model.Todo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class TodoFragment extends Fragment {
    ArrayList<Todo> todoArrayList;
    RecyclerView recyclerView;
    TodoAdapter todoAdapter;

    public TodoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        recyclerView =  view.findViewById(R.id.recycler_view);
        todoArrayList =  new ArrayList<>();
        todoAdapter = new TodoAdapter(getContext(),todoArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(todoAdapter);
        return view;

    }

    private class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

        private Context context;
        ArrayList<Todo> todoArrayList;


        public TodoAdapter(Context context, ArrayList<Todo> todoArrayList) {
            this.context = context;
            this.todoArrayList = todoArrayList;

        }

        @NonNull
        @NotNull
        @Override
        public TodoAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
            TodoAdapter.MyViewHolder holder = new TodoAdapter.MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull TodoAdapter.MyViewHolder holder, int position) {





        }

        @Override
        public int getItemCount() {
            return 12;
        }

        protected class MyViewHolder extends RecyclerView.ViewHolder {





            protected MyViewHolder(View itemView) {
                super(itemView);


            }
        }



    }
}