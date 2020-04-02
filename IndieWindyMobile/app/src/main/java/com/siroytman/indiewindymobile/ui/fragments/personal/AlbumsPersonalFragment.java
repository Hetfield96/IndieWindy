package com.siroytman.indiewindymobile.ui.fragments.personal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AlbumController;
import com.siroytman.indiewindymobile.interfaces.ISearchableAlbum;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.links.UserAlbumLinkListFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class AlbumsPersonalFragment extends Fragment implements ISearchableAlbum {
    public static final String TAG = "AlbumsPersonalFragment";
    private static AlbumsPersonalFragment instance;
    private AlbumController albumController;

    private AlbumsPersonalFragment() {
        albumController = AlbumController.getInstance();
    }

    public static synchronized AlbumsPersonalFragment getInstance() {
        if (instance == null) {
            instance = new AlbumsPersonalFragment();
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
        return inflater.inflate(R.layout.fragment_personal_albums, container, false);
    }

    @Override
    public void albumsFoundViewUpdate(ArrayList<UserAlbumLink> links) {
        if (links.size() > 0) {
            Log.d(TAG, "Albums found");

            // Load list fragment
            UserAlbumLinkListFragment fragment = new UserAlbumLinkListFragment(links, true, false);
            FragmentService.replaceFragment(this, R.id.fragment_personal_albums__container, fragment);
        } else {
            FragmentService.clearFragment(this);
        }
    }
}
