package com.siroytman.indiewindymobile.services;

import android.util.Log;

import com.siroytman.indiewindymobile.ui.activity.PlayerActivity;

public class PlayerServiceConnection {
    public static final String TAG = "PlayerServiceConnection";

    private static PlayerServiceConnection instance;
    private PlayerActivity playerActivity;

    private PlayerServiceConnection(PlayerActivity playerActivity) {
        this.playerActivity = playerActivity;
        instance = this;
    }

    public static void createInstance(PlayerActivity playerActivity) {
        if (instance == null) {
            new PlayerServiceConnection(playerActivity);
        } else {
            instance.playerActivity = playerActivity;
        }
    }

    public static PlayerServiceConnection getInstance()  {
        if(instance == null) {
            Log.d(TAG, "Instance is null");
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public PlayerActivity getPlayerActivity() {
        return playerActivity;
    }
}
