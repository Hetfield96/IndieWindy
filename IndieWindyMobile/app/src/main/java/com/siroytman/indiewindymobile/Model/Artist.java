package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist {
    private int id;

    private String name;

    private String imageUrl;

    public Artist() { }

    public static Artist ParseArtist(JSONObject jsonObject) {
        try {
            Artist artist = new Artist();
            artist.setId(jsonObject.getInt("id"));
            artist.setName(jsonObject.getString("name"));
            artist.setImageUrl(jsonObject.getString("imageUrl"));

            return artist;
        } catch (JSONException e)
        {
            Log.d("ParseArtist", "Error: " + e.getMessage());
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
}
