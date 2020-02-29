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

    // Search (by songs)
    public static void handleSearchIntent(final SearchActivity searchActivity, final ApiController apiController, Intent intent) {
        final Context context = searchActivity.getApplicationContext();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            String url = "song/find/" + query;
            apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
                @Override
                public void onSuccessResponse(JSONArray result) {
                    try {
                        Log.d("Search", "Songs found");
                        searchActivity.SongsFound(ParseSongs(result));
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

    private static ArrayList<Song> ParseSongs(JSONArray songs)
    {
        ArrayList<Song> result = new ArrayList<>();
        for(int i = 0; i < songs.length(); ++i)
        {
            try {
                JSONObject jsonObject = songs.getJSONObject(i);
                Song song = new Song(jsonObject.getString("name"));
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
