package com.siroytman.indiewindymobile.services;

import com.siroytman.indiewindymobile.ui.activity.PlayerActivity;

public class PlayerServiceConnection {
    private static PlayerServiceConnection instance;
    private PlayerActivity playerActivity;

    private PlayerServiceConnection(PlayerActivity playerActivity) {
        this.playerActivity = playerActivity;
        instance = this;
    }

    public static void createInstance(PlayerActivity playerView) {
        if (instance == null) {
            new PlayerServiceConnection(playerView);
        } else {
            instance.playerActivity = playerView;
        }
    }

    public static PlayerServiceConnection getInstance() {
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public PlayerActivity getPlayerActivity() {
        return playerActivity;
    }
}
