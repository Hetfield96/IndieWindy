package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.ui.adapter.UserSongLinkListAdapter;
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

    public void addUserSongLink(final UserSongLinkListAdapter.ViewHolder viewHolder) {
        String url = "userSongLink/add";
        final Song song = viewHolder.getSongLink().getSong();

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("SongId", song.getId());
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d(TAG, "UserSongLink added: " + song.getName());
                viewHolder.songAdded();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: Song not added: " + song.getName());
            }
        });
    }

        public void removeUserSongLink(final UserSongLinkListAdapter.ViewHolder viewHolder) {
            final Song song = viewHolder.getSongLink().getSong();
            String url = "userSongLink/delete/" + AppController.user.getId() + "/" + song.getId();

            apiController.getStringResponse(Request.Method.DELETE, url, new VolleyCallbackString() {
                @Override
                public void onSuccessResponse(String result) {
                    if(result.equals("true")) {
                        viewHolder.songRemoved();
                        Log.d(TAG, "UserSongLink removed: " + song.getName());
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "UserSongLink not removed: " + error.getMessage());
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
