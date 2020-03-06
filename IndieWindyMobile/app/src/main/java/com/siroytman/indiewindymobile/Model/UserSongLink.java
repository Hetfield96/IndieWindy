package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserSongLink {
    public static final String TAG = "UserSongLink";

    // Required
    private int appUserId;
    private int songId;


    // Possible
    private Song song;

    public UserSongLink(){}

    public static UserSongLink Parse(JSONObject json){
        UserSongLink link = new UserSongLink();

        try {
            link.appUserId = json.getInt("appUserId");
            link.songId = json.getInt("songId");
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse link");
            return null;
        }

        try {
            link.song = Song.ParseSong(json.getJSONObject("song"));
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse song");
        }

        return link;
    }

    public static ArrayList<UserSongLink> parseLinks(JSONArray links)
    {
        ArrayList<UserSongLink> result = new ArrayList<>();
        for(int i = 0; i < links.length(); ++i)
        {
            try {
                JSONObject jsonObject = links.getJSONObject(i);
                UserSongLink link = Parse(jsonObject);
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
        return (appUserId == 0 && songId == 0);
    }

    public void makeEmpty(){
        appUserId = 0;
        songId = 0;
    }

    public Song getSong() {
        return song;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public int getSongId() {
        return songId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
