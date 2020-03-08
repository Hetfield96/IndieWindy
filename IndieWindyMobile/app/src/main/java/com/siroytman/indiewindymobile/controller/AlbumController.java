package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.activity.AlbumActivity;

import org.json.JSONArray;

import java.util.ArrayList;

public class AlbumController {
    private static final String TAG = "AlbumController";

    private AlbumActivity albumActivity;
    private ApiController apiController;
    private static AlbumController instance;

    private AlbumController(AlbumActivity albumActivity) {
        apiController = ApiController.getInstance();
        this.albumActivity = albumActivity;
    }

    public static synchronized AlbumController getInstance(AlbumActivity albumActivity) {
        if (instance == null) {
            instance = new AlbumController(albumActivity);
        }
        return instance;
    }

    public void getAlbumSongs(final Album album) {
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
    }}
