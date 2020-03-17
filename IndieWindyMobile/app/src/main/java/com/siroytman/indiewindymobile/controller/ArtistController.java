package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONObject;
import com.siroytman.indiewindymobile.api.VolleyCallbackString;
import com.siroytman.indiewindymobile.interfaces.ILinkAdd;
import com.siroytman.indiewindymobile.interfaces.ISearchableArtist;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.ui.activity.ArtistActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArtistController {
    private static final String TAG = "ArtistController";

    private ApiController apiController;
    private static ArtistController instance;

    private ArtistController() {
        apiController = ApiController.getInstance();
    }

    public static synchronized ArtistController getInstance() {
        if (instance == null) {
            instance = new ArtistController();
        }
        return instance;
    }

    public void searchArtists(final ISearchableArtist view, String query){
        String url = "artist/find/" + query + "/" + AppController.user.getId();
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Artists search: request completed");
                    ArrayList<UserArtistLink> links = UserArtistLink.parseLinks(result);
                    view.artistsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Artists search: request not completed!");
            }
        });
    }

    public void searchArtistsLinked(final ISearchableArtist view) {
        searchArtistsLinked(view, "null");
    }

    public void searchArtistsLinked(final ISearchableArtist view, String query){
        String url = "artist/findLinked/" + query + "/" + AppController.user.getId();
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Artists search: request completed");
                    ArrayList<UserArtistLink> links = UserArtistLink.parseLinks(result);
                    view.artistsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Artists search: request not completed!");
            }
        });
    }

    public void getArtistAlbums(final ArtistActivity artistActivity, Artist artist){
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

    public void linkExist(final ILinkAdd<Artist> view){
        final Artist artist = view.getItem();
        String url = "userArtistLink/linkExist/" + AppController.user.getId() + "/" + artist.getId();

        apiController.getStringResponse(Request.Method.GET, url, new VolleyCallbackString() {
            @Override
            public void onSuccessResponse(String result) {
                if (result.equals("true")) {
                    view.added();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "linkExist error");
            }
        });
    }


    public void addUserArtistLink(final ILinkAdd<Artist> view) {
        String url = "userArtistLink/add";
        final Artist artist = view.getItem();

        Map<String, Integer> postParam = new HashMap<>();
        postParam.put("AppUserId", AppController.user.getId());
        postParam.put("ArtistId", artist.getId());
        apiController.getJSONObjectResponse(url, new JSONObject(postParam), new VolleyCallbackJSONObject() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                Log.d(TAG, "UserArtistLink added: " + artist.getName());
                view.added();
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "UserArtistLink not added: " + artist.getName());
            }
        });
    }

    public void removeUserArtistLink(final ILinkAdd<Artist> view) {
        final Artist artist = view.getItem();
        String url = "userArtistLink/delete/" + AppController.user.getId() + "/" + artist.getId();

        apiController.getStringResponse(Request.Method.DELETE, url, new VolleyCallbackString() {
            @Override
            public void onSuccessResponse(String result) {
                if(result.equals("true")) {
                    view.removed();
                    Log.d(TAG, "UserArtistLink removed: " + artist.getName());
                } else {
                    Log.d(TAG, "UserArtistLink not removed: " + artist.getName());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "UserArtistLink not removed: " + error.getMessage());
            }
        });
    }
}
