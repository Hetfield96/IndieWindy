package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.model.UserConcertLink;
import com.siroytman.indiewindymobile.ui.fragments.concert.NearestConcertFragment;

import org.json.JSONArray;

import java.util.ArrayList;

public class ConcertController {
    private static final String TAG = "ConcertController";
    private ApiController apiController;
    private static ConcertController instance;

    private ConcertController() {
        apiController = ApiController.getInstance();
    }

    public static synchronized ConcertController getInstance() {
        if (instance == null) {
            instance = new ConcertController();
        }
        return instance;
    }

    public void searchConcerts(final NearestConcertFragment concertFragment, String query){
        String url = "concert/find/" + query + "/" + AppController.user.getId();
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Concert search: request completed");
                    ArrayList<UserConcertLink> links = UserConcertLink.parseLinks(result);
                    concertFragment.concertsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Concert search: request not completed!");
            }
        });
    }
}
