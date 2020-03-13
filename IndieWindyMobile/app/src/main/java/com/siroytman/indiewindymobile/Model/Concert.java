package com.siroytman.indiewindymobile.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;


public class Concert implements Parcelable {
    public static final String TAG = "Concert";

    private int id;
    private String name;
    private String imageUrl;
    private int cost;
    private Time startTime;
    private String address;


    public static Concert Parse(JSONObject json) {
        Concert concert = new Concert();
        try {
            concert.id = json.getInt("id");
            concert.name = json.getString("name");
            concert.imageUrl = json.getString("imageUrl");
            concert.cost = json.getInt("cost");
            // TODO check
//            concert.startTime = Time.valueOf(json.getString("startTime"));
            concert.address = json.getString("address");

        } catch (JSONException e)
        {
            Log.d(TAG, "Can't parse concert");
            return null;
        }

        return concert;
    }

    public Concert() {}

    protected Concert(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imageUrl = in.readString();
        cost = in.readInt();
        address = in.readString();
    }

    public static final Creator<Concert> CREATOR = new Creator<Concert>() {
        @Override
        public Concert createFromParcel(Parcel in) {
            return new Concert(in);
        }

        @Override
        public Concert[] newArray(int size) {
            return new Concert[size];
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
        dest.writeInt(cost);
        dest.writeString(address);
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
