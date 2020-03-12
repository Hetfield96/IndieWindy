package com.siroytman.indiewindymobile.ui.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.SearchController;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.FragmentService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private SearchController searchController;

    private SearchView searchView = null;
    // Containers busy state
    private LinkedHashMap<Integer, Boolean> containerBusy;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        searchController = SearchController.getInstance(this);
    }

    public void songsFoundViewUpdate(ArrayList<UserSongLink> links)
    {
        if (links.size() > 0) {
            Log.d(TAG, "Songs found");

            int containerId = R.id.fragment_search__song_container;
            containerBusy.put(containerId, true);

            // Load list fragment
            UserSongLinkListFragment fragment = new UserSongLinkListFragment(links);
            FragmentService.replaceFragment(this, containerId, fragment);
        }
    }

    public void albumsFoundViewUpdate(ArrayList<UserAlbumLink> links)
    {
        if (links.size() > 0) {
            Log.d(TAG, "Albums found");

            int containerId = pickFirstEmptyContainer();
            containerBusy.put(containerId, true);

            // Load list fragment
            UserAlbumLinkListFragment fragment = new UserAlbumLinkListFragment(links);
            FragmentService.replaceFragment(this, containerId, fragment);
        }
    }

    public void artistsFoundViewUpdate(ArrayList<UserArtistLink> links)
    {
        if (links.size() > 0) {
            Log.d(TAG, "Artists found");

            int containerId = pickFirstEmptyContainer();
            containerBusy.put(containerId, true);

            // Load list fragment
            UserArtistLinkListFragment fragment = new UserArtistLinkListFragment(links);
            FragmentService.replaceFragment(this, containerId, fragment);
        }
    }

    // Returns first empty containerId
    private Integer pickFirstEmptyContainer() {
        for (Map.Entry<Integer, Boolean> pair : containerBusy.entrySet()) {
            if (!pair.getValue()) {
                return pair.getKey();
            }
        }
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    hideKeyboard();
                    clearContainers();
                    searchController.search(query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void clearContainers() {
        containerBusy = new LinkedHashMap<Integer, Boolean>() {{
            put(R.id.fragment_search__song_container, false);
            put(R.id.fragment_search__artist_container, false);
            put(R.id.fragment_search__album_container, false);
        }};

        FragmentService.clearFragment(this);
    }
}
