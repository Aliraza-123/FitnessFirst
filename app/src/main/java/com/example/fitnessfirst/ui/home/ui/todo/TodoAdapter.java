package com.example.fitnessfirst.ui.home.ui.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessfirst.R;
import com.example.fitnessfirst.data.local.entities.TodoTask;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The type Todo adapter.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

    private final Context context;
    private List<TodoTask> adapterData;

    private OnDeleteClickListener onDeleteClickListener;
    private OnCheckedClickListener onCheckedClickListener;

    /**
     * Instantiates a new Todo adapter.
     *
     * @param context       the context
     * @param todoArrayList the todo array list
     */
    public TodoAdapter(Context context, List<TodoTask> todoArrayList) {
        this.context = context;
        this.adapterData = todoArrayList;

    }

    /**
     * Sets on delete click listener.
     *
     * @param onDeleteClickListener the on delete click listener
     */
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    /**
     * Sets on checked click listener.
     *
     * @param onCheckedClickListener the on checked click listener
     */
    public void setOnCheckedClickListener(OnCheckedClickListener onCheckedClickListener) {
        this.onCheckedClickListener = onCheckedClickListener;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public TodoAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TodoAdapter.MyViewHolder holder, int position) {

        holder.titleTextView.setText(adapterData.get(position).getTaskTitle());

        if (adapterData.get(position).getIsCompleted() == 0) {
            Glide.with(context)
                    .load(R.drawable.ic_check_false)
                    .into(holder.checkImageView);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_check_true)
                    .into(holder.checkImageView);
        }

        if (onDeleteClickListener != null) {
            holder.deleteImageView.setOnClickListener(v -> onDeleteClickListener.onDeleteClicked(adapterData.get(position)));
        }
        if (onCheckedClickListener != null) {
            holder.checkImageView.setOnClickListener(v -> onCheckedClickListener.onCheckClicked(adapterData.get(position)));
        }
    }

    /**
     * Sets adapter data.
     *
     * @param adapterData the adapter data
     */
    public void setAdapterData(List<TodoTask> adapterData) {
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
     * The interface On delete click listener.
     */
    public interface OnDeleteClickListener {
        /**
         * On delete clicked.
         *
         * @param todoTask the todo task
         */
        void onDeleteClicked(TodoTask todoTask);
    }


    /**
     * The interface On checked click listener.
     */
    public interface OnCheckedClickListener {
        /**
         * On check clicked.
         *
         * @param todoTask the todo task
         */
        void onCheckClicked(TodoTask todoTask);
    }

    /**
     * The type My view holder.
     */
    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView deleteImageView;
        private final TextView titleTextView;
        private final ImageView checkImageView;

        /**
         * Instantiates a new My view holder.
         *
         * @param itemView the item view
         */
        protected MyViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.tv_title);
            deleteImageView = itemView.findViewById(R.id.iv_delete);
            checkImageView = itemView.findViewById(R.id.iv_check);

        }
    }
}
