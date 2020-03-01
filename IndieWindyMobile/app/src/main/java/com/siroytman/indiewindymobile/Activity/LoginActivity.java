package com.siroytman.indiewindymobile.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.LoginController;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    LoginController loginController;

    private EditText nameText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginController = LoginController.getInstance(this);

        nameText = findViewById(R.id.nameText);
        passwordText = findViewById(R.id.passwordText);
    }

    // Authorization
    public void loginOnClick(View view) {
        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "No login or password was entered!", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        loginController.login(name, password);
    }

    // Registration
    public void registerOnClick(View view) {
        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "No login or password was entered!", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        loginController.register(name, password);
    }
}
