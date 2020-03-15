package com.siroytman.indiewindymobile.ui.fragments.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.ui.fragments.concert.NearestConcertFragment;

import androidx.fragment.app.Fragment;

public class SongsPersonalFragment extends Fragment {
    public static final String TAG = "SongsPersonalFragment";
    private static SongsPersonalFragment instance;

    private SongsPersonalFragment() {
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

}
