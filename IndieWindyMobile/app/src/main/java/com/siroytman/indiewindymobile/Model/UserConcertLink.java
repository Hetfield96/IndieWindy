package com.siroytman.indiewindymobile.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserConcertLink {
    public static final String TAG = "UserConcertLink";

    // Required
    private int appUserId;
    private int concertId;

    // Possible
    private Concert concert;

    public UserConcertLink(){}

    public static UserConcertLink Parse(JSONObject json){
        UserConcertLink link = new UserConcertLink();

        try {
            link.appUserId = json.getInt("appUserId");
            link.concertId = json.getInt("concertId");
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse link");
            return null;
        }

        try {
            link.concert = Concert.Parse(json.getJSONObject("concert"));
        } catch (JSONException e) {
            Log.d(TAG, "Can't parse concert");
        }

        return link;
    }

    public static ArrayList<UserConcertLink> parseLinks(JSONArray links)
    {
        ArrayList<UserConcertLink> result = new ArrayList<>();
        for(int i = 0; i < links.length(); ++i)
        {
            try {
                JSONObject jsonObject = links.getJSONObject(i);
                UserConcertLink link = Parse(jsonObject);
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
        return (appUserId == 0 && concertId == 0);
    }

    public void makeEmpty(){
        appUserId = 0;
        concertId = 0;
    }

    public Concert getConcert() {
        return concert;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public int getConcertId() {
        return concertId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }
}
