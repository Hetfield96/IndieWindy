package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.ErrorHandler;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.fragments.SearchFragment;

import org.json.JSONArray;

import java.util.ArrayList;

public class SearchController  {
    private static final String TAG = "SearchController";
    private SearchFragment searchFragment;
    private ApiController apiController;
    private static SearchController instance;

    private SearchController(SearchFragment searchFragment) {
        apiController = ApiController.getInstance();
        this.searchFragment = searchFragment;
    }

    public static synchronized SearchController getInstance(SearchFragment searchFragment) {
        if (instance == null) {
            instance = new SearchController(searchFragment);
        }
        return instance;
    }


    public void search(final String query){
        // Search songs
        final Thread searchSongs = new Thread(new Runnable() {
            @Override
            public void run() {
                searchSongs(query);
            }
        });
        searchSongs.start();

        // Search artists
        final Thread searchArtists = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // When searchSongs is finished
                    searchSongs.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                searchArtists(query);
            }
        });
        searchArtists.start();

        // Search albums
        Thread searchAlbums = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // When searchArtists is finished
                    searchArtists.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                searchAlbums(query);
            }
        });
        searchAlbums.start();
    }

    private void searchSongs(String query){
        String url = "song/find/" + query + "/" + AppController.user.getId();
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Songs search: request completed");
                    ArrayList<UserSongLink> links = UserSongLink.parseLinks(result);
                    searchFragment.songsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(searchFragment.getContext(), error)) {
                    Log.d(TAG, "Songs search: request not completed!");
                }
            }
        });
    }

    private void searchAlbums(String query){
        String url = "album/find/" + query + "/" + AppController.user.getId();
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Albums search: request completed");
                    ArrayList<UserAlbumLink> links = UserAlbumLink.parseLinks(result);
                    searchFragment.albumsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(searchFragment.getContext(), error)) {
                    Log.d(TAG, "Albums search: request not completed!");
                }
            }
        });
    }

    private void searchArtists(String query){
        String url = "artist/find/" + query + "/" + AppController.user.getId();
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "Artists search: request completed");
                    ArrayList<UserArtistLink> links = UserArtistLink.parseLinks(result);
                    searchFragment.artistsFoundViewUpdate(links);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ErrorHandler.HandleError(searchFragment.getContext(), error)) {
                    Log.d(TAG, "Artists search: request not completed!");
                }
            }
        });
    }

}
