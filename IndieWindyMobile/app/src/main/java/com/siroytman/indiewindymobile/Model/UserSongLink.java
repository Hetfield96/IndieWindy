package com.siroytman.indiewindymobile.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.siroytman.indiewindymobile.interfaces.ILinkEmpty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserSongLink implements Parcelable, ILinkEmpty {
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
            link.song = Song.Parse(json.getJSONObject("song"));
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

    @Override
    public boolean isEmpty(){
        return (appUserId == 0 && songId == 0);
    }

    @Override
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

    protected UserSongLink(Parcel in) {
        appUserId = in.readInt();
        songId = in.readInt();
        song = in.readParcelable(Song.class.getClassLoader());
    }

    public static final Creator<UserSongLink> CREATOR = new Creator<UserSongLink>() {
        @Override
        public UserSongLink createFromParcel(Parcel in) {
            return new UserSongLink(in);
        }

        @Override
        public UserSongLink[] newArray(int size) {
            return new UserSongLink[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(appUserId);
        dest.writeInt(songId);
        dest.writeParcelable(song, flags);
    }

}
