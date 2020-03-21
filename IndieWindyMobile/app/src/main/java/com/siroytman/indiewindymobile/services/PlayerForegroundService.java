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
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.Song;
import com.siroytman.indiewindymobile.ui.activity.PlayerActivity;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


public class PlayerForegroundService extends Service {
    public static final String TAG = "PlayerForegroundService";
    private static final String CHANNEL_ID = "ForegroundServiceChannel";


    public static PlayerForegroundService instance;

    private Song song;
    private SimpleExoPlayer player;


    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    public static void startService(Context context, Song song) {
        Log.d(TAG, "startService begin");

        if(instance != null) {
            Log.d(TAG, "instance not null");
            if (instance.song.equals(song)) {
                Log.d(TAG, "Instance have same song");
                // Set new playerView player
                PlayerServiceConnection.getInstance().getPlayerView().setPlayer(instance.player);
                return;
            }

            instance.stopService();
        }

        Intent serviceIntent = new Intent(context, PlayerForegroundService.class);
        serviceIntent.putExtra(Song.class.getSimpleName(), song);
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
            song = arguments.getParcelable(Song.class.getSimpleName());
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        createNotificationChannel();
        Intent notificationIntent = new Intent(this, PlayerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(TAG)
                .setContentText(song.getName())
                .setSmallIcon(R.drawable.ic_album)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        //do heavy work on a background thread

        initializePlayer();
//        stopSelf();
        return START_STICKY;
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
        player = ExoPlayerFactory.newSimpleInstance(this);
        PlayerServiceConnection.getInstance().getPlayerView().setPlayer(player);

        Uri uri = Uri.parse(song.getSongUrl());
        MediaSource mediaSource = buildMediaSource(uri);


        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
        Log.d(TAG, "Play: " + song.getName());
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
            Log.d(TAG, "Stop: " + song.getName());
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "indiewindy_exoplayer");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

}