package com.siti.asyst.session.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siti.asyst.session.R;
import com.siti.asyst.session.model.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Task> mListTask;
    OnItemClickListener listener;

    public TaskAdapter(Context context, ArrayList<Task> listTask, OnItemClickListener listener) {
        this.mContext = context;
        this.mListTask = listTask;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemIV = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new TaskAdapter.MyViewHolder(itemIV);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Task task = mListTask.get(position);

        holder.nameTV.setText(task.getCustomer_name());
        holder.addressTV.setText(task.getCustomer_address());
        holder.startTV.setText(task.getStartDate());
        holder.finishTV.setText(task.getFinishDate());
        holder.customerIDTV.setText(task.getCustomer_id());
        holder.taskIDTV.setText(task.getTask_id());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(task);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListTask.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Task task);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV, addressTV, startTV, finishTV, customerIDTV, taskIDTV;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.name_textview);
            addressTV = itemView.findViewById(R.id.address_textview);
            startTV = itemView.findViewById(R.id.start_textview);
            finishTV = itemView.findViewById(R.id.finishdate_textview);
            customerIDTV = itemView.findViewById(R.id.customerid_textview);
            taskIDTV = itemView.findViewById(R.id.idtask_textview);

            cardView = itemView.findViewById(R.id.cardView);

        }
    }


}
