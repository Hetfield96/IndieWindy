package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.api.VolleyCallbackString;
import com.siroytman.indiewindymobile.interfaces.ILinkAdd;
import com.siroytman.indiewindymobile.interfaces.ISearchableArtist;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.ui.activity.ArtistActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DonationController {
    private static final String TAG = "DonationController";

    private ApiController apiController;
    private static DonationController instance;

    private DonationController() {
        apiController = ApiController.getInstance();
    }

    public static synchronized DonationController getInstance() {
        if (instance == null) {
            instance = new DonationController();
        }
        return instance;
    }

    public void addDonation(final Artist artist, final int donationAmount) {
        String url = "donation/add";

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("ArtistId", artist.getId());
        postParam.put("Amount", donationAmount);
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d(TAG, "donation added:"
                        + "user=" + AppController.user.getName()
                        + "; artist=" + artist.getName()
                        + "; amount=" + donationAmount);
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "donation not added:"
                        + "user=" + AppController.user.getName()
                        + "; artist=" + artist.getName()
                        + "; amount=" + donationAmount);            }
        });
    }

}
