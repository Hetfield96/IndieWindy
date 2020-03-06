package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.adapter.SearchRecyclerViewAdapter;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.api.VolleyCallbackString;
import com.siroytman.indiewindymobile.model.AppUser;
import com.siroytman.indiewindymobile.model.Song;
import com.siroytman.indiewindymobile.model.UserSongLink;

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

    public void AddUserSongLink(final SearchRecyclerViewAdapter.ViewHolder viewHolder, final int songId) {
        String url = "userSongLink/add";

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("SongId", songId);
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d(TAG, "UserSongLink added: " + songId);
                viewHolder.songSetIconCheck();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: Song not added: " + songId);
            }
        });
    }

    // TODO not used
//    public void IsSongAdded(final Song song, final SearchRecyclerViewAdapter.ViewHolder viewHolder) {
//        String url = "userSongLink/isSongAdded/" + AppController.user.getId() + "/" + song.getId();
//
//        // Maybe move callback creation from controller to callers further
//        apiController.getStringResponse(Request.Method.GET, url, new VolleyCallbackString() {
//            @Override
//            public void onSuccessResponse(String result) {
//                if(result.equals("true")) {
//                    viewHolder.songSetIconCheck();
//                    Log.d(TAG, "songIsAdded: " + song.getName());
//                }
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "Error: " + error.getMessage());
//            }
//        });
//    }
}
