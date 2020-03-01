package com.siroytman.indiewindymobile.controller;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.activity.LoginActivity;
import com.siroytman.indiewindymobile.activity.SearchActivity;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.model.AppUser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private ApiController apiController;
    private LoginActivity loginActivity;
    private static LoginController instance;

    private LoginController(LoginActivity LoginActivity) {
        this.loginActivity = LoginActivity;
        apiController = ApiController.getInstance();
    }

    public static synchronized LoginController getInstance(LoginActivity LoginActivity) {
        if (instance == null) {
            instance = new LoginController(LoginActivity);
        }
        return instance;
    }

    public void login(String name, String password){
        Map<String, String> postParam = new HashMap<>();
        postParam.put("Name", name);
        postParam.put("Password", password);
        apiController.getJSONObjectResponse("appuser/login", new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                try {
                    AppController.user = AppUser.ParseAppUser(result);
                    Toast.makeText(loginActivity, "Hello, " + AppController.user.getName() + "!", Toast.LENGTH_LONG)
                            .show();
                    // Swap to next activity
                    loginActivity.startActivity(new Intent(loginActivity, SearchActivity.class));
                    loginActivity.finish();
                }
                catch (Exception e)
                {
                    Log.d("VolleyError", "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(loginActivity, error)) {
                    Toast.makeText(loginActivity, "Incorrect login or password!" + error.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public void register(String name, String password){
        Map<String, String> postParam = new HashMap<>();
        postParam.put("Name", name);
        postParam.put("Password", password);
        apiController.getJSONObjectResponse("appuser/register", new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                try {
                    AppController.user = AppUser.ParseAppUser(result);
                    Toast.makeText(loginActivity, "Hello, " + AppController.user.getName() + "!", Toast.LENGTH_LONG)
                            .show();
                    // Swap to next activity
                    loginActivity.startActivity(new Intent(loginActivity, SearchActivity.class));
                    loginActivity.finish();
                }
                catch (Exception e)
                {
                    Log.d("VolleyError", "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(loginActivity, error)) {
                    Toast.makeText(loginActivity, "User with that login already exists!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
