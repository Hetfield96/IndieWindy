package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserAlbumLink {
    public static final String TAG = "UserAlbumLink";

    // Required
    private int appUserId;
    private int albumId;

    // Possible
    private Album album;

    public UserAlbumLink(){}

    public static UserAlbumLink Parse(JSONObject json){
        UserAlbumLink link = new UserAlbumLink();

        try {
            link.appUserId = json.getInt("appUserId");
            link.albumId = json.getInt("albumId");
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse link");
            return null;
        }

        try {
            link.album = Album.Parse(json.getJSONObject("album"));
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse album");
        }

        return link;
    }

    public static ArrayList<UserAlbumLink> parseLinks(JSONArray links)
    {
        ArrayList<UserAlbumLink> result = new ArrayList<>();
        for(int i = 0; i < links.length(); ++i)
        {
            try {
                JSONObject jsonObject = links.getJSONObject(i);
                UserAlbumLink link = Parse(jsonObject);
                result.add(link);
            }
            catch (JSONException e)
            {
                Log.d(TAG, "Can't parse array of links");
            }
        }
        return result;
    }

    public boolean isEmpty(){
        return (appUserId == 0 && albumId == 0);
    }

    public void makeEmpty(){
        appUserId = 0;
        albumId = 0;
    }

    public Album getAlbum() {
        return album;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}