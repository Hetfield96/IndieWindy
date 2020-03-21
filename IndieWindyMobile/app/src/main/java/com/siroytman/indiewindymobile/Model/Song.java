package com.siroytman.indiewindymobile.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;

public class Song implements Parcelable {
    public static final String TAG = "Song";

    private int id;
    private String name;
    private String songUrl;

    private Artist artist;
    private Album album;

    public Song() {}

    public static Song Parse(JSONObject json) {
        Song song = new Song();
        try {
            song.id = json.getInt("id");
            song.name = json.getString("name");
            song.songUrl = json.getString("songUrl");
        } catch (JSONException e)
        {
            Log.d(TAG, "Can't parse song");
            return null;
        }

        try {
            song.artist = Artist.Parse(json.getJSONObject("artist"));
        } catch (JSONException e) {
            Log.d(TAG,"No artist");
        }

        try {
            Album album = Album.Parse(json.getJSONObject("album"), song.artist);
            song.album = album;
        } catch (JSONException e) {
            Log.d(TAG,"No album");
        }

        return song;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Song or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Song)) {
            return false;
        }

        // typecast o to Song so that we can compare data members
        Song songObj = (Song) obj;

        // Compare ids
        return id == songObj.id;
    }

    protected Song(Parcel in) {
        id = in.readInt();
        name = in.readString();
        songUrl = in.readString();
        artist = in.readParcelable(Artist.class.getClassLoader());
        album = in.readParcelable(Album.class.getClassLoader());
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
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
        dest.writeString(songUrl);
        dest.writeParcelable(artist, flags);
        dest.writeParcelable(album, flags);
    }
}
