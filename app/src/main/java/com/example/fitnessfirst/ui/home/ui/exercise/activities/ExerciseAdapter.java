package com.example.fitnessfirst.ui.home.ui.exercise.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.data.local.entities.Exercise;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The type Exercise adapter.
 */
public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {

    private final Context context;
    private List<Exercise> adapterData;

    /**
     * Instantiates a new Exercise adapter.
     *
     * @param context       the context
     * @param todoArrayList the todo array list
     */
    public ExerciseAdapter(Context context, List<Exercise> todoArrayList) {
        this.context = context;
        this.adapterData = todoArrayList;

    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ExerciseAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_exercise, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ExerciseAdapter.MyViewHolder holder, int position) {

        holder.titleTextView.setText("" + adapterData.get(position).getExTitle());
        holder.descTextView.setText("" + adapterData.get(position).getExDesc());

    }

    /**
     * Sets adapter data.
     *
     * @param adapterData the adapter data
     */
    public void setAdapterData(List<Exercise> adapterData) {
        this.adapterData = adapterData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (adapterData != null)
            return adapterData.size();
        else return 0;
    }


    /**
     * The type My view holder.
     */
    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final TextView descTextView;

        /**
         * Instantiates a new My view holder.
         *
         * @param itemView the item view
         */
        protected MyViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.et_title);
            descTextView = itemView.findViewById(R.id.et_desc);

        }
    }
}
