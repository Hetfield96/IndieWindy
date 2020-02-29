package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Song {
    // TODO do we need id?
    private int id;
    private String name;
    private String songUrl;

    private Artist artist;
    private Album album;

    public Song() {}

    public static Song ParseSong(JSONObject jsonObject) {
        try {
            Song song = new Song();
            song.setId(jsonObject.getInt("id"));
            song.setName(jsonObject.getString("name"));
            song.setSongUrl(jsonObject.getString("songUrl"));

            Artist artist = Artist.ParseArtist(jsonObject.getJSONObject("artist"));
            song.setArtist(artist);

            Album album = Album.ParseAlbum(jsonObject.getJSONObject("album"), artist);
            song.setAlbum(album);

            return song;

        } catch (JSONException e)
        {
            Log.d("ParseSong", "Error: " + e.getMessage());
            return null;
        }
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
