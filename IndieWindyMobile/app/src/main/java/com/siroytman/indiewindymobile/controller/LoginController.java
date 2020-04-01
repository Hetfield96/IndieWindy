package com.siroytman.indiewindymobile.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.model.AppUser;
import com.siroytman.indiewindymobile.services.SharedPrefsService;
import com.siroytman.indiewindymobile.ui.activity.LoginActivity;
import com.siroytman.indiewindymobile.ui.activity.NavigationActivity;
import com.siroytman.indiewindymobile.ui.activity.PlayerActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    public static final String TAG = "LoginController";

    private ApiController apiController;
    private SharedPrefsService sharedPrefsService;

    private static LoginController instance;

    private LoginController() {
        apiController = ApiController.getInstance();
        sharedPrefsService = new SharedPrefsService();
    }

    public static synchronized LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public void login(LoginActivity loginActivity, String name, String password){
        login(loginActivity, name, password, false);
    }

    private void login(final LoginActivity loginActivity, String name, String password, Boolean passwordIsHashed){
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

                    String msg = String.format(loginActivity.getString(R.string.login__hello), AppController.user.getName());
                    Toast.makeText(loginActivity, msg, Toast.LENGTH_LONG)
                            .show();
                    // Swap to next activity
                    loginActivity.startActivity(new Intent(loginActivity, NavigationActivity.class));
                    loginActivity.finish();
                }
                catch (Exception e)
                {
                    loginActivity.stopLoadingProgressBar();
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                loginActivity.stopLoadingProgressBar();
                if (!ErrorHandler.HandleError(loginActivity, error)) {
                    Toast.makeText(loginActivity, loginActivity.getString(R.string.login__incorrect_login),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public void loginFromSharedPrefs(LoginActivity loginActivity){
        AppUser user = sharedPrefsService.getAppUser();
        if (user != null){
            loginActivity.startLoadingProgressBar();
            login(loginActivity, user.getName(), user.getPassword(), true);

            Log.d(TAG, "Login by shared prefs");
        }
        else{
            Log.d(TAG, "No shared prefs exists for login");
            loginActivity.stopLoadingProgressBar();
        }
    }

    public void logout(Context context) {
        removeSharedPrefs();
        AppController.user = null;
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public void removeSharedPrefs(){
        sharedPrefsService.remove();
    }

    public void register(final LoginActivity loginActivity, String name, String password){
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

                String msg = String.format(loginActivity.getString(R.string.login__hello), AppController.user.getName());
                Toast.makeText(loginActivity, msg, Toast.LENGTH_LONG)
                        .show();
                // Swap to next activity
                loginActivity.startActivity(new Intent(loginActivity, NavigationActivity.class));
                loginActivity.finish();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(loginActivity, error)) {
                    Toast.makeText(loginActivity, loginActivity.getString(R.string.login__user_exist),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
