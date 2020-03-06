package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Album {
    private int id;
    private String name;
    private String imageUrl;
    private Artist artist;

    public Album() { }

    // TODO refactor parsing
    public static Album Parse(JSONObject jsonObject) {
        try {
            Album album = new Album();
            album.setId(jsonObject.getInt("id"));
            album.setName(jsonObject.getString("name"));
            album.setImageUrl(jsonObject.getString("imageUrl"));

            Artist artist = Artist.ParseArtist(jsonObject.getJSONObject("artist"));
            album.setArtist(artist);

            return album;
        } catch (JSONException e)
        {
            Log.d("ParseAlbum", "Error: " + e.getMessage());
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
