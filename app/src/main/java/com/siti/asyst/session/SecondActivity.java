package com.siti.asyst.session;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.siti.asyst.session.adapter.TaskAdapter;
import com.siti.asyst.session.model.Task;
import com.siti.asyst.session.retrofit.ApiClient;
import com.siti.asyst.session.retrofit.ApiServices;
import com.siti.asyst.session.utility.SessionUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    SessionUtil sessionUtil;
    ArrayList<Task> listTask = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(taskAdapter);

        taskAdapter = new TaskAdapter(this, listTask, new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                Toast.makeText(getApplicationContext(), task.getCustomer_name(), Toast.LENGTH_SHORT).show();
            }
        });

        sessionUtil = new SessionUtil(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(taskAdapter);
        getData();

    }

    public void getData() {
        ApiServices apiServices = ApiClient.newInstance(getApplicationContext()).create(ApiServices.class);
        Call<ArrayList<Task>> call = apiServices.getTask();

        call.enqueue(new Callback<ArrayList<Task>>() {
            @Override
            public void onResponse(Call<ArrayList<Task>> call, Response<ArrayList<Task>> response) {
                if (response.body() != null) {
                    if (response.body().size() > 0) {

                        listTask.addAll(response.body());
                        taskAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Task>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
