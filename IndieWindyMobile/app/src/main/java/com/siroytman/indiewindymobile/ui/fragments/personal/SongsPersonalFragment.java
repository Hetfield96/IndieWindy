package com.siroytman.indiewindymobile.ui.fragments.personal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.SongController;
import com.siroytman.indiewindymobile.interfaces.ISearchableSong;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.links.UserSongLinkListFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class SongsPersonalFragment extends Fragment implements ISearchableSong {
    public static final String TAG = "SongsPersonalFragment";
    private static SongsPersonalFragment instance;
    private SongController songController;

    private SongsPersonalFragment() {
        songController = SongController.getInstance();
        songController.searchSongsLinked(this);
    }

    public static synchronized SongsPersonalFragment getInstance() {
        if (instance == null) {
            instance = new SongsPersonalFragment();
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
        return inflater.inflate(R.layout.fragment_personal_songs, container, false);
    }

    @Override
    public void songsFoundViewUpdate(ArrayList<UserSongLink> links) {
        if (links.size() > 0) {
            Log.d(TAG, "Songs found");

            // Load list fragment
            UserSongLinkListFragment fragment = new UserSongLinkListFragment(links);
            FragmentService.replaceFragment(this, R.id.fragment_personal_songs__container, fragment);
        } else {
            FragmentService.clearFragment(this);
        }
    }
}
