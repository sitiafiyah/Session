package com.siti.asyst.session;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.siti.asyst.session.adapter.TaskAdapter;
import com.siti.asyst.session.model.Login;
import com.siti.asyst.session.model.Task;
import com.siti.asyst.session.retrofit.ApiClient;
import com.siti.asyst.session.retrofit.ApiServices;
import com.siti.asyst.session.retrofit.request.TaskRequest;
import com.siti.asyst.session.retrofit.response.TaskResponse;
import com.siti.asyst.session.utility.SessionUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    ProgressBar progressBar;
    SessionUtil sessionUtil;
    ArrayList<Task> listTask = new ArrayList<>();

    // String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        progressBar = findViewById(R.id.progressbar);

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
        getDataTask();

    }

    public void getDataTask() {

        TaskRequest taskRequest = new TaskRequest();
        final Login login = new Login();

        taskRequest.setMethod("getAllTask");
        login.setUsername(sessionUtil.loadUsername());
        taskRequest.setParam(login);

        ApiServices apiServices = ApiClient.newInstance(getApplicationContext()).create(ApiServices.class);

        Call<TaskResponse> call = apiServices.getTask(taskRequest);

        //TaskResponse taskResponse = new TaskResponse();
        //Log.d("haha", "haha " + taskResponse.getData());

        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.body() != null) {
                    if (response.body().getData().size() > 0) {
                        listTask.addAll(response.body().getData());
                        taskAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.logout:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirmation")
                        .setCancelable(false)
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sessionUtil.saveLogin("", "");
                                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();



                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
