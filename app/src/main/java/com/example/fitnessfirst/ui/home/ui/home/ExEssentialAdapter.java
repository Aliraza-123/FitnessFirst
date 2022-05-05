package com.example.fitnessfirst.ui.home.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessfirst.R;
import com.example.fitnessfirst.data.local.entities.ExerciseEssentials;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExEssentialAdapter extends RecyclerView.Adapter<ExEssentialAdapter.MyViewHolder> {

    private final Context context;
    private List<ExerciseEssentials> adapterData;

    public ExEssentialAdapter(Context context, List<ExerciseEssentials> todoArrayList) {
        this.context = context;
        this.adapterData = todoArrayList;

    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ExEssentialAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_essentials, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ExEssentialAdapter.MyViewHolder holder, int position) {

        holder.titleTextView.setText("" + adapterData.get(position).getEssentialTitle());
        holder.descTextView.setText("" + adapterData.get(position).getEssentialDesc());

    }

    public void setAdapterData(List<ExerciseEssentials> adapterData) {
        this.adapterData = adapterData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (adapterData != null)
            return adapterData.size();
        else return 0;
    }


    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final TextView descTextView;

        protected MyViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.et_title);
            descTextView = itemView.findViewById(R.id.et_desc);

        }
    }
}
