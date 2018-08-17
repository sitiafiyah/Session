package com.siti.asyst.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.siti.asyst.session.utility.SessionUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameET, passwordET;
    Button loginButton;

    SessionUtil sessionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionUtil = new SessionUtil(this);

        usernameET = findViewById(R.id.username_edittext);
        passwordET = findViewById(R.id.password_edittext);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        //mengecek apakah kita sudah login apa belum
        if (sessionUtil.getLogin()) {
            startActivity(new Intent(MainActivity.this, SecondActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

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
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

}
