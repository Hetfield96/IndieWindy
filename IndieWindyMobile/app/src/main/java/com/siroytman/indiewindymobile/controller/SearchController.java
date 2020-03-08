package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.fragments.SearchFragment;

import org.json.JSONArray;

import java.util.ArrayList;

public class SearchController  {
    private static final String TAG = "SearchController";
    private SearchFragment searchFragment;
    private ApiController apiController;
    private static SearchController instance;

    private SearchController(SearchFragment searchFragment) {
        apiController = ApiController.getInstance();
        this.searchFragment = searchFragment;
    }

    public static synchronized SearchController getInstance(SearchFragment searchFragment) {
        if (instance == null) {
            instance = new SearchController(searchFragment);
        }
        return instance;
    }

    // Search (by songs)
    public void search(String query){
        String url = "song/findWithAdded/" + query + "/" + AppController.user.getId();
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Songs found");
                    ArrayList<UserSongLink> links = UserSongLink.parseLinks(result);
                    searchFragment.LinksViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(searchFragment.getContext(), error)) {
                    Log.d(TAG, "Songs not found!");
                }
            }
        });
    }

}
