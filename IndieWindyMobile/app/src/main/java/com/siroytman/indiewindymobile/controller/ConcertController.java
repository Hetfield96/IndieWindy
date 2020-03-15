package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.api.VolleyCallbackString;
import com.siroytman.indiewindymobile.interfaces.ILinkActions;
import com.siroytman.indiewindymobile.model.Concert;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.model.UserConcertLink;
import com.siroytman.indiewindymobile.ui.activity.ConcertActivity;
import com.siroytman.indiewindymobile.ui.fragments.concert.NearestConcertFragment;
import com.siroytman.indiewindymobile.ui.fragments.concert.SavedConcertFragment;
import com.siroytman.indiewindymobile.ui.fragments.concert.SubscriptionConcertFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void getNearestConcerts() {
        NearestConcertFragment.getInstance();
        getNearestConcerts("null");
    }

    public void getNearestConcerts(String query){
        String url = "concert/getNearest/" + AppController.user.getId() + "/" + query;
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "getNearestConcerts: search request completed");
                    ArrayList<UserConcertLink> links = UserConcertLink.parseLinks(result);
                    NearestConcertFragment.getInstance().concertsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "getNearestConcerts: search request not completed!" + error.getMessage());
            }
        });
    }

    public void getSubscriptionConcerts() {
        getSubscriptionConcerts("null");
    }

    public void getSubscriptionConcerts(String query){
        String url = "concert/getBySubscription/" + AppController.user.getId() + "/" + query;
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "getSubscriptionConcerts: search request completed");
                    ArrayList<UserConcertLink> links = UserConcertLink.parseLinks(result);
                    SubscriptionConcertFragment.getInstance().concertsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "getSubscriptionConcerts: search request not completed!" + error.getMessage());
            }
        });
    }

    public void getSavedConcerts() {
        getSavedConcerts("null");
    }

    public void getSavedConcerts(String query){
        String url = "concert/getSaved/" + AppController.user.getId() + "/" + query;
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "getSavedConcerts: search request completed");
                    ArrayList<UserConcertLink> links = UserConcertLink.parseLinks(result);
                    SavedConcertFragment.getInstance().concertsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "getSavedConcerts: search request not completed!" + error.getMessage());
            }
        });
    }

    public void getArtists(final ConcertActivity concertActivity, final int concertId){
        String url = "concert/" + AppController.user.getId() + "/" + concertId + "/artists";
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Artists search: request completed");
                    ArrayList<UserArtistLink> links = UserArtistLink.parseLinks(result);
                    concertActivity.artistsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Artists search: request not completed!");
            }
        });
    }

    public void addUserConcertLink(final ILinkActions<Concert> view) {
        String url = "userConcertLink/add";
        final Concert concert = view.getItem();

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("ConcertId", concert.getId());
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d(TAG, "userConcertLink added: " + concert.getName());
                view.added();
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "userConcertLink not added: " + concert.getName());
            }
        });
    }

    public void removeUserConcertLink(final ILinkActions<Concert> view) {
        final Concert concert = view.getItem();
        String url = "userConcertLink/delete/" + AppController.user.getId() + "/" + concert.getId();

        apiController.getStringResponse(Request.Method.DELETE, url, new VolleyCallbackString() {
            @Override
            public void onSuccessResponse(String result) {
                if(result.equals("true")) {
                    view.removed();
                    Log.d(TAG, "userConcertLink removed: " + concert.getName());
                } else {
                    Log.d(TAG, "userConcertLink not removed: " + concert.getName());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "userConcertLink not removed: " + error.getMessage());
            }
        });
    }
}
