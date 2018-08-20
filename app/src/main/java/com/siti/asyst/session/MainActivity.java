package com.siti.asyst.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.siti.asyst.session.model.Login;
import com.siti.asyst.session.retrofit.ApiClient;
import com.siti.asyst.session.retrofit.ApiServices;
import com.siti.asyst.session.retrofit.request.LoginRequest;
import com.siti.asyst.session.retrofit.response.LoginResponse;
import com.siti.asyst.session.utility.SessionUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameET, passwordET;
    Button loginButton;
    ProgressBar progressBar;

    SessionUtil sessionUtil;

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionUtil = new SessionUtil(this);

        usernameET = findViewById(R.id.username_edittext);
        passwordET = findViewById(R.id.password_edittext);
        progressBar = findViewById(R.id.progressbar);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        checkLogin();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                if (TextUtils.isEmpty(usernameET.getText().toString())) {
                    usernameET.setError("Input Your Username");
                } else if (TextUtils.isEmpty(passwordET.getText().toString())) {
                    passwordET.setError("Input Your Password");
                } else {
                    doLogin();
                }
                break;
        }
    }

    private void doLogin() {
        username = usernameET.getText().toString();
        password = passwordET.getText().toString();

        LoginRequest loginRequest = new LoginRequest();
        final Login login = new Login();

        loginRequest.setMethod("getProfileInfo");
        login.setUsername(username);
        login.setPassword(password);
        loginRequest.setParam(login);

        ApiServices apiServices = ApiClient.newInstance(getApplicationContext()).create(ApiServices.class);
        Call<LoginResponse> call = apiServices.getLogin(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //progressBar.setVisibility(View.INVISIBLE);

                if (response.body().getStatus().equalsIgnoreCase("success")) {
                    //User user = new User();
                    //Log.d("username", "username" + login.getUsername());
                    Toast.makeText(getApplicationContext(), "Welcome " + login.getUsername(), Toast.LENGTH_SHORT).show();

                    sessionUtil.saveLogin(login.getUsername(), login.getPassword());
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Your username or password invalid", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void checkLogin() {

        if (!sessionUtil.loadUsername().equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Welcome " + sessionUtil.loadUsername(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
