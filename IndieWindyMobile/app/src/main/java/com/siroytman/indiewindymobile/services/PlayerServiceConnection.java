package com.siroytman.indiewindymobile.services;

import com.google.android.exoplayer2.ui.PlayerView;

public class PlayerServiceConnection {
    private static PlayerServiceConnection instance;
    private PlayerView playerView;

    private PlayerServiceConnection(PlayerView playerView) {
        this.playerView = playerView;
        instance = this;
    }

    public static void createInstance(PlayerView playerView) {
        if (instance == null) {
            new PlayerServiceConnection(playerView);
        } else {
            instance.playerView = playerView;
        }
    }

    public static PlayerServiceConnection getInstance() {
        return instance;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }
}
