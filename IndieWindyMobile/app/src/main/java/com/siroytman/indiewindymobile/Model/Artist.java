package com.siroytman.indiewindymobile.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist implements Parcelable {
    private int id;

    private String name;

    private String imageUrl;

    public Artist() { }


    public static Artist ParseArtist(JSONObject jsonObject) {
        try {
            Artist artist = new Artist();
            artist.setId(jsonObject.getInt("id"));
            artist.setName(jsonObject.getString("name"));
            artist.setImageUrl(jsonObject.getString("imageUrl"));

            return artist;
        } catch (JSONException e)
        {
            Log.d("ParseArtist", "Error: " + e.getMessage());
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


    protected Artist(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
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
}
