package com.siroytman.indiewindymobile.ui.fragments.personal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.ArtistController;
import com.siroytman.indiewindymobile.interfaces.ISearchableArtist;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.links.UserArtistLinkListFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class ArtistsPersonalFragment extends Fragment implements ISearchableArtist {
    public static final String TAG = "ArtistsPersonalFragment";
    private static ArtistsPersonalFragment instance;
    private ArtistController artistController;

    private ArtistsPersonalFragment() {
        artistController = ArtistController.getInstance();
        artistController.searchArtistsLinked(this);
    }

    public static synchronized ArtistsPersonalFragment getInstance() {
        if (instance == null) {
            instance = new ArtistsPersonalFragment();
        }
        return instance;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_artists, container, false);
    }

    @Override
    public void artistsFoundViewUpdate(ArrayList<UserArtistLink> links) {
        if (links.size() > 0) {
            Log.d(TAG, "Artists found");

            // Load list fragment
            UserArtistLinkListFragment fragment = new UserArtistLinkListFragment(links, true);
            FragmentService.replaceFragment(this, R.id.fragment_personal_artists__container, fragment);
        } else {
            FragmentService.clearFragment(this);
        }
    }
}
