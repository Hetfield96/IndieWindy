package com.siroytman.indiewindymobile.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.links.UserAlbumLinkListFragment;
import com.siroytman.indiewindymobile.ui.fragments.links.UserArtistLinkListFragment;
import com.siroytman.indiewindymobile.ui.fragments.links.UserSongLinkListFragment;
import com.siroytman.indiewindymobile.ui.fragments.search.SearchFragment;

import java.util.ArrayList;

public class MoreActivity extends AppCompatActivity {
    public static final String TAG = "MoreActivity";
    private ArrayList<UserSongLink> songLinks;
    private ArrayList<UserAlbumLink> albumLinks;
    private ArrayList<UserArtistLink> artistLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_more);

        // Get songLinks from bundle
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            songLinks = arguments.getParcelableArrayList("songLinks");
            albumLinks = arguments.getParcelableArrayList("albumLinks");
            artistLinks = arguments.getParcelableArrayList("artistLinks");
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        if (songLinks != null) {
            // Load list fragment
            UserSongLinkListFragment fragment = new UserSongLinkListFragment(songLinks, true);
            FragmentService.replaceFragment(this, R.id.more_activity__container, fragment);
        }

        if (albumLinks != null) {
            // Load list fragment
            UserAlbumLinkListFragment fragment = new UserAlbumLinkListFragment(albumLinks, true);
            FragmentService.replaceFragment(this, R.id.more_activity__container, fragment);
        }

        if (artistLinks != null) {
            // Load list fragment
            UserArtistLinkListFragment fragment = new UserArtistLinkListFragment(artistLinks, true);
            FragmentService.replaceFragment(this, R.id.more_activity__container, fragment);
        }
    }
}
