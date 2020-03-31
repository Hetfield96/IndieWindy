package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArtistPostLink {
    public static final String TAG = "ArtistPostLink";

    // Required
    private int artistId;
    private int postId;

    // Possible
    private Artist artist;
    private Post post;

    public ArtistPostLink(){}


    public static ArtistPostLink Parse(JSONObject json){
        ArtistPostLink link = new ArtistPostLink();

        try {
            link.artistId = json.getInt("artistId");
            link.postId = json.getInt("postId");
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse link");
            return null;
        }

        try {
            link.post = Post.parse(json.getJSONObject("post"));
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse post");
        }

        try {
            link.artist = Artist.parse(json.getJSONObject("artist"));
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse artist");
        }

        return link;
    }

    public static ArrayList<ArtistPostLink> parseArray(JSONArray links)
    {
        ArrayList<ArtistPostLink> result = new ArrayList<>();
        for(int i = 0; i < links.length(); ++i)
        {
            try {
                JSONObject jsonObject = links.getJSONObject(i);
                ArtistPostLink link = Parse(jsonObject);
                result.add(link);
            }
            catch (JSONException e)
            {
                Log.d(TAG, "Can't parse array of links");
            }
        }
        return result;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
