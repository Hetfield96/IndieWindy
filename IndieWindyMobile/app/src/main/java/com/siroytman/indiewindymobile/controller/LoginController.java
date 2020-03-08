package com.siroytman.indiewindymobile.controller;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.model.AppUser;
import com.siroytman.indiewindymobile.services.SharedPrefsService;
import com.siroytman.indiewindymobile.ui.activity.LoginActivity;
import com.siroytman.indiewindymobile.ui.activity.NavigationActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    public static final String TAG = "LoginController";

    private ApiController apiController;
    private SharedPrefsService sharedPrefsService;

    private LoginActivity loginActivity;
    private static LoginController instance;

    private LoginController(LoginActivity LoginActivity) {
        this.loginActivity = LoginActivity;
        apiController = ApiController.getInstance();
        sharedPrefsService = new SharedPrefsService();
    }

    public static synchronized LoginController getInstance(LoginActivity LoginActivity) {
        if (instance == null) {
            instance = new LoginController(LoginActivity);
        }
        return instance;
    }

    public void login(String name, String password){
        login(name, password, false);
    }

    private void login(String name, String password, Boolean passwordIsHashed){
        String url = "appuser/login/" + passwordIsHashed;

        Map<String, String> postParam = new HashMap<>();
        postParam.put("Name", name);
        postParam.put("Password", password);
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                try {
                    AppController.user = AppUser.ParseAppUser(result);
                    // Save to shared prefs
                    sharedPrefsService.saveAppUser(AppController.user);

                    Toast.makeText(loginActivity, "Hello, " + AppController.user.getName() + "!", Toast.LENGTH_SHORT)
                            .show();
                    // Swap to next activity
                    loginActivity.startActivity(new Intent(loginActivity, NavigationActivity.class));
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
                    Toast.makeText(loginActivity, "Incorrect login or password!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }


    public void loginFromSharedPrefs(){
        AppUser user = sharedPrefsService.getAppUser();
        if (user != null){
            login(user.getName(), user.getPassword(), true);

            Log.d(TAG, "Login by shared prefs");
        }
        else{
            Log.d(TAG, "No shared prefs exists for login");
        }
    }

    public void removeSharedPrefs(){
        sharedPrefsService.remove();
    }

    public void register(String name, String password){
        String url = "appuser/register";

        Map<String, String> postParam = new HashMap<>();
        postParam.put("Name", name);
        postParam.put("Password", password);
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                AppController.user = AppUser.ParseAppUser(result);
                // Save to shared prefs
                sharedPrefsService.saveAppUser(AppController.user);

                Toast.makeText(loginActivity, "Hello, " + AppController.user.getName() + "!", Toast.LENGTH_LONG)
                        .show();
                // Swap to next activity
                loginActivity.startActivity(new Intent(loginActivity, NavigationActivity.class));
                loginActivity.finish();
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
