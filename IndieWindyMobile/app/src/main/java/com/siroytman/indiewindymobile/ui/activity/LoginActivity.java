package com.siroytman.indiewindymobile.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.LoginController;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";

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
        loginController = LoginController.getInstance();

        nameText = findViewById(R.id.login_activity__login);
        passwordText = findViewById(R.id.login_activity__password);
        progressBar = findViewById(R.id.login_activity__loading_progress_bar);
        loginButton = findViewById(R.id.login_activity__login_button);
        registerButton = findViewById(R.id.login_activity__register_button);

        loginController.loginFromSharedPrefs(this);
    }

    public void setLoginAndPasswordText(String login, String password){
        nameText.setText(login);
        // Password is hashed here... Should be not
    }

    public void startLoadingProgressBar() {
        Log.d(TAG, "startLoadingProgressBar");
        passwordText.setEnabled(false);
        nameText.setEnabled(false);
        loginButton.setEnabled(false);
        registerButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoadingProgressBar() {
        Log.d(TAG, "stopLoadingProgressBar");
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
            Toast.makeText(this, getString(R.string.login__no_login), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        loginController.login(this, name, password);
    }

    // Registration
    public void registerOnClick(View view) {
        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.login__no_login), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        loginController.register(this, name, password);
    }
}
