package com.siroytman.indiewindymobile.services;

import com.google.android.exoplayer2.ui.PlayerView;

public class PlayerServiceConnection {
    private static PlayerServiceConnection instance;

    public PlayerView playerView;

    public PlayerServiceConnection(PlayerView playerView) {
        this.playerView = playerView;
        instance = this;
    }

    public static PlayerServiceConnection getInstance() {
        return instance;
    }
}
