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
    private static final String TAG = "SongController";
    private ApiController apiController;

    private static SongController instance;

    private SongController() {
        apiController = ApiController.getInstance();
    }

    public static synchronized SongController getInstance() {
        if (instance == null) {
            instance = new SongController();
        }
        return instance;
    }

    public void AddSong(final SearchRecyclerViewAdapter.ViewHolder viewHolder, final int songId) {
        String url = "userSongLink/add";

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("SongId", songId);
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d(TAG, "Song added: " + songId);
                viewHolder.songAddedChangeIcon();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: Song not added: " + songId);
            }
        });
    }
}
