package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Post {
    public static final String TAG = "Post";

    private int id;
    private String text;
    private Timestamp time;
    private String imageUrl;
    private int artistId;


    public static Post parse(JSONObject json) {
        Post post = new Post();
        try {
            post.id = json.getInt("id");
            post.text = json.getString("text");
            post.time = Timestamp.valueOf(json.getString("time").replace("T", " "));
            post.imageUrl = json.getString("imageUrl");
            post.artistId = json.getInt("artistId");

        } catch (Exception e)
        {
            Log.d(TAG, "Can't parse post: " + e.getMessage());
            return null;
        }

        return post;
    }

    public static ArrayList<Post> parseArray(JSONArray posts)
    {
        ArrayList<Post> result = new ArrayList<>();
        for(int i = 0; i < posts.length(); ++i)
        {
            try {
                JSONObject jsonObject = posts.getJSONObject(i);
                Post link = parse(jsonObject);
                result.add(link);
            }
            catch (JSONException e)
            {
                Log.d(TAG, "Can't parse array of posts");
            }
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
