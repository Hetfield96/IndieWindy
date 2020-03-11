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
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.FragmentService;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private SearchController searchController;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

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

    public void songFoundViewUpdate(ArrayList<UserSongLink> links)
    {
        // Load list fragment
        UserSongLinkListFragment fragment = new UserSongLinkListFragment(links);
        FragmentService.replaceFragment(this, R.id.fragment_search_container, fragment);
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

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    hideKeyboard();

                    Log.d(TAG, "Query submit: " + query);
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
}
