package com.siroytman.indiewindymobile.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.model.AppUser;

public class SharedPrefsService {
    private SharedPreferences preferences;
    private Gson gson;

    public SharedPrefsService() {
        Context context = AppController.getContext();
        this.preferences = context.getSharedPreferences("IndieWindyPrefs", context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveAppUser(AppUser user){
        String json = gson.toJson(user);
        preferences.edit().putString("AppUser", json).apply();
    }

    public AppUser getAppUser(){
        String json = preferences.getString("AppUser", "");
        if (json.isEmpty()){
            return null;
        }
        return gson.fromJson(json, AppUser.class);
    }

    public void remove(){
        preferences.edit().clear().apply();
    }

}
