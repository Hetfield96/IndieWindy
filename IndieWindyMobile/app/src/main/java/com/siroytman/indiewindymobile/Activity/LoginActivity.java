package com.siroytman.indiewindymobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.model.AppUser;
import com.siroytman.indiewindymobile.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    ApiController apiController;

    private EditText nameText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiController = ApiController.getInstance(this);

        nameText = findViewById(R.id.nameText);
        passwordText = findViewById(R.id.passwordText);
    }

    // Authorization
    public void loginOnClick(View view) {
        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || password.isEmpty())
        {
            Toast.makeText(LoginActivity.this, "No login or password was entered!", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Map<String, String> postParam = new HashMap<>();
        postParam.put("Name", name);
        postParam.put("Password", password);
        apiController.getJSONObjectResponse("appuser/login", new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                try {
                    AppController.user = apiController.gson.fromJson(result.toString(), AppUser.class);
                    Toast.makeText(LoginActivity.this, "Hello, " + AppController.user.name + "!", Toast.LENGTH_LONG)
                            .show();
                    // Swap to main activity
                    startActivity(new Intent(LoginActivity.this, SearchActivity.class));
                    finish();
                }
                catch (Exception e)
                {
                    Log.d("VolleyError", "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(LoginActivity.this, error)) {
                    Toast.makeText(LoginActivity.this, "Incorrect login or password!" + error.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    // Registration
    public void registerOnClick(View view)
    {
        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || password.isEmpty())
        {
            Toast.makeText(LoginActivity.this, "No login or password was entered!", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Map<String, String> postParam = new HashMap<>();
        postParam.put("Name", name);
        postParam.put("Password", password);
        apiController.getJSONObjectResponse("appuser/register", new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                try {
                    AppController.user = apiController.gson.fromJson(result.toString(), AppUser.class);
                    Toast.makeText(LoginActivity.this, "Hello, " + AppController.user.name + "!", Toast.LENGTH_LONG)
                            .show();
                    // Swap to main activity
                    startActivity(new Intent(LoginActivity.this, SearchActivity.class));
                    finish();
                }
                catch (Exception e)
                {
                    Log.d("VolleyError", "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(LoginActivity.this, error)) {
                    Toast.makeText(LoginActivity.this, "User with that login already exists!" + error.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
