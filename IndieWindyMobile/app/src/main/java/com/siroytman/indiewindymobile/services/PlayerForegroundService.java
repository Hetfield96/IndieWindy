package com.siroytman.indiewindymobile.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
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
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.Song;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.activity.PlayerActivity;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


public class PlayerForegroundService extends Service {
    public static final String TAG = "PlayerForegroundService";
    private static final String CHANNEL_ID = "ForegroundServiceChannel";
    private static final int FOREGROUND_NOTIFICATION_ID = 1;

    public static PlayerForegroundService instance;

    private UserSongLink songLink;

    private ArrayList<UserSongLink> songLinks;
    private ConcatenatingMediaSource concatenatingMediaSource;

    private PlayerServiceConnection playerActivityConnection;

    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow;
    private long playbackPosition = 0;

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

        createNotification();

        initializePlayer();
//        stopSelf();
        return START_NOT_STICKY;
    }

    private void createNotification() {
        Context context = getApplicationContext();

        createNotificationChannel();

        // adding action to prev button
        Intent prevIntent = new Intent(getApplicationContext(), PlayerNotificationIntentService.class);
        prevIntent.setAction("prev");

        // adding action to next button
        Intent nextIntent = new Intent(this, PlayerNotificationIntentService.class);
        nextIntent.setAction("next");

        // adding action to next button
        Intent pauseIntent = new Intent(this, PlayerNotificationIntentService.class);
        pauseIntent.setAction("next");

        // adding action to click notification
        // TODO
        Intent contentIntent = new Intent(context, PlayerActivity.class);

        Notification notification = new Notification.Builder(context, CHANNEL_ID)
                // Add the metadata for the currently playing track
                .setContentTitle(songLink.getSong().getName())
                .setContentText(songLink.getSong().getArtist().getName())

                // Enable launching the player by clicking the notification
                // TODO
//                .setContentIntent(PendingIntent.getActivity(context, 0, contentIntent, 0))

                // Stop the service when the notification is swiped away
                // TODO
//                .setDeleteIntent()

                // Make the transport controls visible on the lockscreen
                .setVisibility(Notification.VISIBILITY_PUBLIC)

                .addAction(R.drawable.ic_prev, "prev", PendingIntent.getService(context, 0, prevIntent, 0))
                .addAction(R.drawable.ic_pause, "pause", PendingIntent.getService(context, 0, pauseIntent, 0))
                .addAction(R.drawable.ic_next, "next", PendingIntent.getService(context, 0, nextIntent, 0))

                // Add an app icon and set its color
                .setSmallIcon(R.drawable.ic_artist)
                .setColor(ContextCompat.getColor(context, R.color.primary_dark))

                // Take advantage of MediaStyle features
                .setStyle(new Notification.MediaStyle()
//                        .setMediaSession(mediaSession.getSessionToken())
                        .setShowActionsInCompactView(0, 1, 2)
                )
                .build();

        startForeground(FOREGROUND_NOTIFICATION_ID, notification);
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
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private void initializePlayer() {
        Log.d(TAG, "initializePlayer");
        player = ExoPlayerFactory.newSimpleInstance(this);

        PlayerServiceConnection.getInstance().getPlayerActivity().setPlayer(player);
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

}