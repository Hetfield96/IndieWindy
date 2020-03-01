package com.siroytman.indiewindymobile.controller;

import android.app.Application;
import android.content.Context;

import com.siroytman.indiewindymobile.model.AppUser;

public class AppController extends Application {
    public static AppUser user;

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

    private static AppController mInstance;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }
}
