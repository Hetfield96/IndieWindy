package com.siroytman.indiewindymobile.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.siroytman.indiewindymobile.model.UserSongLink;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


public class PlayerForegroundService extends Service {
    public static final String TAG = "PlayerForegroundService";
    private static final String CHANNEL_ID = "PlayerForegroundServiceChannel";
    private static final int NOTIFICATION_ID = 2;

    public static PlayerForegroundService instance;

    private UserSongLink songLink;

    private ArrayList<UserSongLink> songLinks;
    private ConcatenatingMediaSource concatenatingMediaSource;

    private PlayerServiceConnection playerActivityConnection;

    PlayerNotificationManager playerNotificationManager;

    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow;
    private long playbackPosition = 0;

    public static synchronized PlayerForegroundService getInstance() {
        return instance;
    }

    public static void startService(Context context, ArrayList<UserSongLink> songLinks, int songPos) {
        Log.d(TAG, "startService begin");

        if(instance != null) {
            Log.d(TAG, "instance not null");
            if (instance.songLink.getSong().equals(songLinks.get(songPos).getSong())) {
                Log.d(TAG, "Instance have same song");
                // Set new playerView player
                PlayerServiceConnection.getInstance().getPlayerActivity().setPlayer(instance.player);
                return;
            }

            instance.stopService();
        }

        Intent serviceIntent = new Intent(context, PlayerForegroundService.class);
        serviceIntent.putExtra("songLinks", songLinks);
        serviceIntent.putExtra("songPos", songPos);
        ContextCompat.startForegroundService(context, serviceIntent);

        Log.d(TAG, "startService end");
    }


//    public static void stopService(Context context) {
//        Intent serviceIntent = new Intent(context, PlayerForegroundService.class);
//        stopService(serviceIntent);
//    }

    public void stopService() {
        Log.d(TAG, "stopService begin");
        instance = null;
        stopSelf();
        Log.d(TAG, "stopService end");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        Log.d(TAG, "Destroyed");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate begin");
        super.onCreate();
        instance = this;

        playerNotificationManager = new PlayerNotificationManager(
                getApplicationContext(),
                CHANNEL_ID,
                NOTIFICATION_ID,
                new PlayerNotificationDescriptionAdapter());

        Log.d(TAG, "onCreate end");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle arguments = intent.getExtras();
        if(arguments != null) {
            songLinks = arguments.getParcelableArrayList("songLinks");
            currentWindow = arguments.getInt("songPos");
            songLink = songLinks.get(currentWindow);
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        initializePlayer();
        createNotification();
        return START_NOT_STICKY;
    }

    private void createNotification() {
        Context context = getApplicationContext();

        createNotificationChannel();

        Notification notification = new Notification.Builder(context, CHANNEL_ID)
                // Make the transport controls visible on the lockscreen
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                // Take advantage of MediaStyle features
                .setStyle(new Notification.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                )
                .build();

        startForeground(NOTIFICATION_ID, notification);
    }

    private void initializePlayer() {
        Log.d(TAG, "initializePlayer");
        player = ExoPlayerFactory.newSimpleInstance(this);
        concatenatingMediaSource = buildConcatenatingMediaSource(songLinks);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(concatenatingMediaSource, false, false);
        Log.d(TAG, "Play: " + songLink.getSong().getName());

        player.addListener(new Player.EventListener() {
            @Override
            public void onPositionDiscontinuity(int reason) {
                //THIS METHOD GETS CALLED FOR EVERY NEW SOURCE THAT IS PLAYED
                int latestWindowIndex = player.getCurrentWindowIndex();
                if (latestWindowIndex == currentWindow) {
                    return;
                }

                // item selected in playlist has changed, handle here
                currentWindow = latestWindowIndex;
                songLink = songLinks.get(currentWindow);

                Log.d(TAG, "Song in playlist changed: pos=" + currentWindow + "; songName=" + songLink.getSong().getName());

                playerActivityConnection = PlayerServiceConnection.getInstance();
                if (playerActivityConnection != null) {
                    playerActivityConnection.getPlayerActivity().updateSongView(currentWindow);
                }
            }
        });

        PlayerServiceConnection.getInstance().getPlayerActivity().setPlayer(player);
    }

    private ConcatenatingMediaSource buildConcatenatingMediaSource(ArrayList<UserSongLink> songLinks) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "indiewindy_exoplayer");
        ConcatenatingMediaSource concatenatingMediaSource = new ConcatenatingMediaSource();

        for (int i = 0; i < songLinks.size(); ++i) {
            Uri uri = Uri.parse(songLinks.get(i).getSong().getSongUrl());

            concatenatingMediaSource.addMediaSource(
                    new ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(uri));
        }
        return concatenatingMediaSource;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_ID,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            serviceChannel.setSound(null, null);

            playerNotificationManager.setPlayer(player);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
            Log.d(TAG, "Stop: " + songLink.getSong().getName());
        }
    }


    private class PlayerNotificationDescriptionAdapter implements
            PlayerNotificationManager.MediaDescriptionAdapter
    {
        public static final String TAG = "PlayerNotificationDescriptionAdapter";

        @Override
        public String getCurrentContentTitle(Player player) {
            return songLink.getSong().getName();
        }

        @Nullable
        @Override
        public String getCurrentContentText(Player player) {
            return songLink.getSong().getArtist().getName();
        }

        @Nullable
        @Override
        public Bitmap getCurrentLargeIcon(Player player,
                                          PlayerNotificationManager.BitmapCallback callback) {
            return null;
        }

        @Nullable
        @Override
        public PendingIntent createCurrentContentIntent(Player player) {
            int window = player.getCurrentWindowIndex();
            return PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(), window);
        }
    }

}