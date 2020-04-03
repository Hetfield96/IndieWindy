package com.siroytman.indiewindymobile.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.model.Artist;

import org.json.JSONObject;

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

    public void addDonation(final Context context, final Artist artist, final int donationAmount) {
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
                Toast.makeText(context, R.string.donation_activity__donation_message, Toast.LENGTH_LONG)
                        .show();
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "donation not added:"
                        + "user=" + AppController.user.getName()
                        + "; artist=" + artist.getName()
                        + "; amount=" + donationAmount);
                Toast.makeText(context, R.string.donation_activity__donation_message, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}
