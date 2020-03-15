package com.siroytman.indiewindymobile.controller;

import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.interfaces.ISearchableAlbum;
import com.siroytman.indiewindymobile.interfaces.ISearchableArtist;
import com.siroytman.indiewindymobile.interfaces.ISearchableSong;

public class SearchController  {
    private static final String TAG = "SearchController";
    private ApiController apiController;
    private static SearchController instance;

    private SearchController() {
        apiController = ApiController.getInstance();
    }

    public static synchronized SearchController getInstance() {
        if (instance == null) {
            instance = new SearchController();
        }
        return instance;
    }

    public void searchSongs(final ISearchableSong view, final String query){
        SongController.getInstance().searchSongs(view, query);
    }

    public void searchArtists(final ISearchableArtist view, String query){
        ArtistController.getInstance().searchArtists(view, query);
    }

    public void searchAlbums(final ISearchableAlbum view, String query){
        AlbumController.getInstance().searchAlbums(view, query);
    }

}
