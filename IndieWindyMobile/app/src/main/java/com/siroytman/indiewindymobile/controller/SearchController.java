package com.siroytman.indiewindymobile.controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.activity.SearchActivity;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchController  {

    private ApiController apiController;
    private SearchActivity searchActivity;
    private static SearchController instance;

    private SearchController(SearchActivity searchActivity) {
        this.searchActivity = searchActivity;
        apiController = ApiController.getInstance();
    }

    public static synchronized SearchController getInstance(SearchActivity searchActivity) {
        if (instance == null) {
            instance = new SearchController(searchActivity);
        }
        // Always last search activity here - specific of search activity
        instance.searchActivity = searchActivity;
        return instance;
    }

    // Search (by songs)
    public void handleSearchIntent(Intent intent) {
        final Context context = searchActivity.getApplicationContext();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            String url = "song/find/" + query;
            apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
                @Override
                public void onSuccessResponse(JSONArray result) {
                    try {
                        Log.d("Search", "Songs found");
                        searchActivity.SongsFoundUpdate(parseSongs(result));
                    }
                    catch (Exception e)
                    {
                        Log.d("Search", "Unable to parse response: " + e.getMessage());
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ErrorHandler.HandleError(context, error)) {
                        Log.d("Search", "Songs not found!");
                    }
                }
            });
        }
    }

    private static ArrayList<Song> parseSongs(JSONArray songs)
    {
        ArrayList<Song> result = new ArrayList<>();
        for(int i = 0; i < songs.length(); ++i)
        {
            try {
                JSONObject jsonObject = songs.getJSONObject(i);
                Song song = Song.ParseSong(jsonObject);
                result.add(song);
            }
            catch (JSONException e)
            {
                Log.d("Search", "Error: " + e.getMessage());
            }
        }
        return result;
    }

}
