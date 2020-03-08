package com.siroytman.indiewindymobile.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Album  implements Parcelable {
    private int id;
    private String name;
    private String imageUrl;
    private Artist artist;

    public Album() { }

    protected Album(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(imageUrl);
    }

    // TODO refactor
    public static Album Parse(JSONObject jsonObject) {
        try {
            Album album = new Album();
            album.setId(jsonObject.getInt("id"));
            album.setName(jsonObject.getString("name"));
            album.setImageUrl(jsonObject.getString("imageUrl"));

            Artist artist = Artist.ParseArtist(jsonObject.getJSONObject("artist"));
            album.setArtist(artist);

            return album;
        } catch (JSONException e)
        {
            Log.d("ParseAlbum", "Error: " + e.getMessage());
            return null;
        }
    }

    public static Album Parse(JSONObject jsonObject, Artist artist) {
        try {
            Album album = new Album();
            album.setId(jsonObject.getInt("id"));
            album.setName(jsonObject.getString("name"));
            album.setImageUrl(jsonObject.getString("imageUrl"));

            album.setArtist(artist);

            return album;
        } catch (JSONException e)
        {
            Log.d("ParseAlbum", "Error: " + e.getMessage());
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
