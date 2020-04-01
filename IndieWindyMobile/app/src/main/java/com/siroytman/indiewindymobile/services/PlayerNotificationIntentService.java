package com.siroytman.indiewindymobile.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

public class PlayerNotificationIntentService extends IntentService {
    public static final String TAG = "PlayerNotificationIntentService";

    public PlayerNotificationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        switch (intent.getAction()) {
            case "prev":
                Log.d(TAG, "Prev clicked");
//                Handler prevHandler = new Handler(Looper.getMainLooper());
//                prevHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d(TAG, "Prev clicked");
//                    }
//                });
                break;
            case "next":
                Log.d(TAG, "Next clicked");
                break;
        }
    }
}
