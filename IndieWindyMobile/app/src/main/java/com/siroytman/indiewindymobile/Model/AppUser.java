package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class AppUser {

    private int id;

    private String name;

    private String password;

    private AppUser() {
    }

    public static AppUser ParseAppUser(JSONObject jsonObject) {
        try {
            AppUser user = new AppUser();
            user.setId(jsonObject.getInt("id"));
            user.setName(jsonObject.getString("name"));

            return user;

        } catch (JSONException e)
        {
            Log.d("ParseAppUser", "Error: " + e.getMessage());
            return null;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
