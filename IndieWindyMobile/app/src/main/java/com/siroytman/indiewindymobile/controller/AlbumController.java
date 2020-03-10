package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.api.VolleyCallbackString;
import com.siroytman.indiewindymobile.interfaces.ILinkActions;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.activity.AlbumActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlbumController {
    private static final String TAG = "AlbumController";

    private ApiController apiController;
    private static AlbumController instance;

    private AlbumController() {
        apiController = ApiController.getInstance();
    }

    public static synchronized AlbumController getInstance() {
        if (instance == null) {
            instance = new AlbumController();
        }
        return instance;
    }

    public void getAlbumSongs(final AlbumActivity albumActivity) {
        final Album album = albumActivity.getItem();
        String url = "album/" + AppController.user.getId() + "/" + album.getId() + "/songs";

        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Songs found");
                    ArrayList<UserSongLink> links = UserSongLink.parseLinks(result);
                    albumActivity.songsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(albumActivity, error)) {
                    Log.d(TAG, "Songs not found!");
                }
            }
        });
    }


    public void addUserAlbumLink(final ILinkActions<Album> view) {
        String url = "userAlbumLink/add";
        final Album album = view.getItem();

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("AlbumId", album.getId());
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d(TAG, "UserAlbumLink added: " + album.getName());
                view.added();
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "UserAlbumLink not added: " + album.getName());
            }
        });
    }

    public void removeUserAlbumLink(final ILinkActions<Album> view) {
        final Album album = view.getItem();
        String url = "userAlbumLink/delete/" + AppController.user.getId() + "/" + album.getId();

        apiController.getStringResponse(Request.Method.DELETE, url, new VolleyCallbackString() {
            @Override
            public void onSuccessResponse(String result) {
                if(result.equals("true")) {
                    view.removed();
                    Log.d(TAG, "UserAlbumLink removed: " + album.getName());
                } else {
                    Log.d(TAG, "UserAlbumLink not removed: " + album.getName());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "UserAlbumLink not removed: " + error.getMessage());
            }
        });
    }
}
