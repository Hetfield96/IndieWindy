package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.activity.ArtistActivity;

import org.json.JSONArray;

import java.util.ArrayList;

public class ArtistController {
    private static final String TAG = "ArtistController";

    private ArtistActivity artistActivity;
    private ApiController apiController;
    private static ArtistController instance;

    private ArtistController(ArtistActivity artistActivity) {
        apiController = ApiController.getInstance();
        this.artistActivity = artistActivity;
    }

    public static synchronized ArtistController getInstance(ArtistActivity artistActivity) {
        if (instance == null) {
            instance = new ArtistController(artistActivity);
        }
        return instance;
    }

    public void getArtistAlbums(Artist artist){
        String url = "artist/" + AppController.user.getId() + "/" + artist.getId() + "/albums";
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Albums found");
                    ArrayList<UserAlbumLink> links = UserAlbumLink.parseLinks(result);
                    artistActivity.albumsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(artistActivity, error)) {
                    Log.d(TAG, "Albums not found!");
                }
            }
        });
    }
}
