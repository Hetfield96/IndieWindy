package com.siroytman.indiewindymobile.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.sql.Timestamp;


public class Concert implements Parcelable {
    public static final String TAG = "Concert";

    private int id;
    private String name;
    private String imageUrl;
    private int cost;
    private Timestamp startTime;
    private String address;
    private String description;
    private String ticketLink;


    public static Concert Parse(JSONObject json) {
        Concert concert = new Concert();
        try {
            concert.id = json.getInt("id");
            concert.name = json.getString("name");
            concert.imageUrl = json.getString("imageUrl");
            concert.cost = json.getInt("cost");
            concert.startTime = Timestamp.valueOf(json.getString("startTime").replace("T", " "));
            concert.address = json.getString("address");
            concert.description = json.getString("description");
            concert.ticketLink = json.getString("ticketLink");

        } catch (Exception e)
        {
            Log.d(TAG, "Can't parse concert: " + e.getMessage());
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
        description = in.readString();
        ticketLink = in.readString();
        startTime = Timestamp.valueOf(in.readString());
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
        dest.writeString(description);
        dest.writeString(ticketLink);
        dest.writeString(startTime.toString());
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public String getStartTimeString() {
        String res = startTime.toString();
        return res.substring(0, res.lastIndexOf(':'));
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public String getTicketLink() {
        return ticketLink;
    }
}
