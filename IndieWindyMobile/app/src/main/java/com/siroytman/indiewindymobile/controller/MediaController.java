package com.siroytman.indiewindymobile.controller;

import android.media.MediaPlayer;
import android.util.Log;

import com.siroytman.indiewindymobile.model.Song;

import java.io.IOException;

public class MediaController {
    public static final String TAG = "Media:";
    private MediaPlayer mediaPlayer;
    private static MediaController instance;
    public Song currentSong;

    private MediaController() {
        mediaPlayer = new MediaPlayer();
    }

    public static synchronized MediaController getInstance() {
        if (instance == null) {
            instance = new MediaController();
        }
        return instance;
    }

    public void setAndPrepareSong(Song song, MediaPlayer.OnPreparedListener onPreparedListener){
        // Rests & set song
        setDataSource(song);

        // Prepare song
        mediaPlayer.setOnPreparedListener(onPreparedListener);
        mediaPlayer.prepareAsync();
        Log.d(TAG + "setAndPrepareSong", "Song preparing: " + currentSong.getName());
    }

    public void startPauseSong(){
        if (!isPlaying()){
            startSong();
        } else {
            pauseSong();
        }
    }

    public void startSong(){
        mediaPlayer.start();
        Log.d(MediaController.TAG + "startSong", "Song started: " + currentSong.getName());
    }

    public void pauseSong(){
        mediaPlayer.pause();
        Log.d(MediaController.TAG + "pauseSong", "Song paused:" + currentSong.getName());
    }

    public void stopSong(){
        mediaPlayer.stop();
        Log.d(TAG + "stopSong", "Song stopped:" + currentSong.getName());
    }


    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }

    public boolean isCurrentSong(Song song){
        if (currentSong == null)
            return false;
        return currentSong.equals(song);
    }

    public boolean hasSong(){
        return currentSong != null;
    }

    private void setDataSource(Song song) {
        try {
            reset();

            mediaPlayer.setDataSource(song.getSongUrl());
            this.currentSong = song;
            Log.d(TAG + "setDataSource", "Song set: " + song.getName());
        } catch (IOException e) {
            Log.d(TAG + "setDataSource", "Error: song not set: " + song.getName());
        }
    }

    private void reset(){
        if (isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
        currentSong = null;
        Log.d(TAG + "reset", "Done");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (mediaPlayer != null) {
            reset();
            mediaPlayer.release();
        }
    }
}
