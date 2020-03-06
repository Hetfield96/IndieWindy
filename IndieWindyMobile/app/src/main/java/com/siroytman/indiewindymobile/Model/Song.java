package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Song {
    public static final String TAG = "Song";

    // TODO do we need id?
    private int id;
    private String name;
    private String songUrl;

    private Artist artist;
    private Album album;

    public Song() {}

    public static Song ParseSong(JSONObject json) {
        Song song = new Song();
        try {
            song.id = json.getInt("id");
            song.name = json.getString("name");
            song.songUrl = json.getString("songUrl");
        } catch (JSONException e)
        {
            Log.d(TAG, "Can't parse song");
            return null;
        }

        try {
            Artist artist = Artist.ParseArtist(json.getJSONObject("artist"));
            song.artist = artist;
        } catch (JSONException e) {
            Log.d(TAG,"No artist");
        }

        try {
            Album album = Album.Parse(json.getJSONObject("album"));
            song.album = album;
        } catch (JSONException e) {
            Log.d(TAG,"No album");
        }

        return song;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
