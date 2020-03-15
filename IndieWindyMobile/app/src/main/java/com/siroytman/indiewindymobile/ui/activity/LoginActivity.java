package com.siroytman.indiewindymobile.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.LoginController;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    LoginController loginController;

    private EditText nameText;
    private EditText passwordText;
    private ProgressBar progressBar;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginController = LoginController.getInstance(this);

        nameText = findViewById(R.id.nameText);
        passwordText = findViewById(R.id.passwordText);
        progressBar = findViewById(R.id.loading_progress_bar);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginController.loginFromSharedPrefs();
    }

    public void startLoadingProgressBar() {
        passwordText.setEnabled(false);
        nameText.setEnabled(false);
        loginButton.setEnabled(false);
        registerButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoadingProgressBar() {
        passwordText.setEnabled(true);
        nameText.setEnabled(true);
        loginButton.setEnabled(true);
        registerButton.setEnabled(true);
        progressBar.setVisibility(View.INVISIBLE);
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
