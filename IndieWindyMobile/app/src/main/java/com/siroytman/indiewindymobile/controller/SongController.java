package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.interfaces.ILinkActions;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.api.VolleyCallbackString;
import com.siroytman.indiewindymobile.model.Song;

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

    public void addUserSongLink(final ILinkActions<Song> view) {
        String url = "userSongLink/add";
        final Song song = view.getItem();

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("SongId", song.getId());
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d(TAG, "UserSongLink added: " + song.getName());
                view.added();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: Song not added: " + song.getName());
            }
        });
    }

        public void removeUserSongLink(final ILinkActions<Song> view) {
            final Song song = view.getItem();
            String url = "userSongLink/delete/" + AppController.user.getId() + "/" + song.getId();

            apiController.getStringResponse(Request.Method.DELETE, url, new VolleyCallbackString() {
                @Override
                public void onSuccessResponse(String result) {
                    if(result.equals("true")) {
                        view.removed();
                        Log.d(TAG, "UserSongLink removed: " + song.getName());
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "UserSongLink not removed: " + error.getMessage());
                }
            });
    }

}
