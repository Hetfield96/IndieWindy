package com.siroytman.indiewindymobile.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.siroytman.indiewindymobile.interfaces.ILinkEmpty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserAlbumLink implements Parcelable, ILinkEmpty {
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

    @Override
    public boolean isEmpty(){
        return (appUserId == 0 && albumId == 0);
    }

    @Override
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(appUserId);
        dest.writeInt(albumId);
        dest.writeParcelable(album, flags);
    }

    protected UserAlbumLink(Parcel in) {
        appUserId = in.readInt();
        albumId = in.readInt();
        album = in.readParcelable(Album.class.getClassLoader());
    }

    public static final Creator<UserAlbumLink> CREATOR = new Creator<UserAlbumLink>() {
        @Override
        public UserAlbumLink createFromParcel(Parcel in) {
            return new UserAlbumLink(in);
        }

        @Override
        public UserAlbumLink[] newArray(int size) {
            return new UserAlbumLink[size];
        }
    };
}
