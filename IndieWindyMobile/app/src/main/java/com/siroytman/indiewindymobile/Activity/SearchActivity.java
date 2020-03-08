package com.siroytman.indiewindymobile.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.activity.fragments.UserSongLinkListFragment;
import com.siroytman.indiewindymobile.controller.SearchController;
import com.siroytman.indiewindymobile.model.UserSongLink;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class SearchActivity extends AppCompatActivity {

    SearchController searchController;
    private ArrayList<UserSongLink> linksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchController = SearchController.getInstance(this);
        searchController.handleSearchIntent(getIntent());
    }

    public void LinksViewUpdate(ArrayList<UserSongLink> links)
    {
        linksList = links;

        // Load list fragment
        UserSongLinkListFragment fragment = new UserSongLinkListFragment(links);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.myContainer, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        searchController.handleSearchIntent(intent);
    }

}
