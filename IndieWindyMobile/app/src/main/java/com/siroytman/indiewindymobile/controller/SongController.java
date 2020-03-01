package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.adapter.SearchRecyclerViewAdapter;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SongController {

    public static void AddSong(final SearchRecyclerViewAdapter.ViewHolder viewHolder, int songId) {
        // TODO refactor
        ApiController apiController;
        try {
            apiController = ApiController.getInstance();
        }
        catch (Exception e)
        {
            return;
        }
        String url = "userSongLink/add";

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("SongId", songId);
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d("SongController", "Song added!");
                viewHolder.songAddedChangeIcon();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SongController", "Error: Song not added!");
            }
        });
    }
}
