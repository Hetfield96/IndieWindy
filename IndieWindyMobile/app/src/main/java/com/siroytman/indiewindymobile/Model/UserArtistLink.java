package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserArtistLink {
    public static final String TAG = "UserArtistLink";

    // Required
    private int appUserId;
    private int artistId;

    // Possible
    private Artist artist;

    public UserArtistLink(){}

    public static UserArtistLink Parse(JSONObject json){
        UserArtistLink link = new UserArtistLink();

        try {
            link.appUserId = json.getInt("appUserId");
            link.artistId = json.getInt("artistId");
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse link");
            return null;
        }

        try {
            link.artist = Artist.Parse(json.getJSONObject("artist"));
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse artist");
        }

        return link;
    }

    public static ArrayList<UserArtistLink> parseLinks(JSONArray links)
    {
        ArrayList<UserArtistLink> result = new ArrayList<>();
        for(int i = 0; i < links.length(); ++i)
        {
            try {
                JSONObject jsonObject = links.getJSONObject(i);
                UserArtistLink link = Parse(jsonObject);
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
        return (appUserId == 0 && artistId == 0);
    }

    public void makeEmpty(){
        appUserId = 0;
        artistId = 0;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
