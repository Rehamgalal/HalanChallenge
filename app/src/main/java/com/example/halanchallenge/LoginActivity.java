package com.example.halanchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText userNameEt, passwordEt;
    Button loginBtn;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_button);

        login = new Login();



        if (userNameEt.getText().length() >= 6 && userNameEt.getText().length() <= 16) {
        loginBtn.setOnClickListener(view -> login.login(userNameEt.getText().toString(),
                passwordEt.getText().toString(),getApplicationContext()));}

    }
}

