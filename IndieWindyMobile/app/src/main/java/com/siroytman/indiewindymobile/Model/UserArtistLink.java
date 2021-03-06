package com.siroytman.indiewindymobile.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.siroytman.indiewindymobile.interfaces.ILinkEmpty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserArtistLink implements Parcelable, ILinkEmpty {
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
            link.artist = Artist.parse(json.getJSONObject("artist"));
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

    @Override
    public boolean isEmpty(){
        return (appUserId == 0 && artistId == 0);
    }

    @Override
    public void makeEmpty(){
        appUserId = 0;
        artistId = 0;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(appUserId);
        dest.writeInt(artistId);
        dest.writeParcelable(artist, flags);
    }

    protected UserArtistLink(Parcel in) {
        appUserId = in.readInt();
        artistId = in.readInt();
        artist = in.readParcelable(Artist.class.getClassLoader());
    }

    public static final Creator<UserArtistLink> CREATOR = new Creator<UserArtistLink>() {
        @Override
        public UserArtistLink createFromParcel(Parcel in) {
            return new UserArtistLink(in);
        }

        @Override
        public UserArtistLink[] newArray(int size) {
            return new UserArtistLink[size];
        }
    };
}
