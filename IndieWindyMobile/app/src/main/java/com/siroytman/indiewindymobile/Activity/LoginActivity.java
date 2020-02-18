package com.siroytman.indiewindymobile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.Api.ApiController;
import com.siroytman.indiewindymobile.Api.VolleyJSONCallback;
import com.siroytman.indiewindymobile.Core;
import com.siroytman.indiewindymobile.Model.AppUser;
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

    public void loginOnClick(View view) {
        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || password.isEmpty())
        {
            MakeToastMessage("No login or password is entered", Toast.LENGTH_LONG);
            return;
        }

        Map<String, String> postParam = new HashMap<>();
        postParam.put("Name", name);
        postParam.put("Password", password);
        apiController.getStringResponseJSON("appuser/login", new JSONObject(postParam), new VolleyJSONCallback() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Core.user = apiController.gson.fromJson(result.toString(), AppUser.class);
                MakeToastMessage("Hello, " + Core.user.name + "!", Toast.LENGTH_LONG);
                // Swap to main activity
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                MakeToastMessage("Incorrect login or password!", Toast.LENGTH_LONG);
            }
        });
    }

    public void MakeToastMessage(String msg, int ToastLength)
    {
        Toast.makeText(this, msg, ToastLength).show();
    }

}
