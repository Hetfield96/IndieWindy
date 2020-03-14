package com.siroytman.indiewindymobile.ui.fragments.concert;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.ConcertController;
import com.siroytman.indiewindymobile.model.UserConcertLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.UserConcertLinkListFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class SubscriptionConcertFragment extends Fragment {
    public static final String TAG = "NearestConcertFragment";
    private ConcertController concertController;

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        concertController = ConcertController.getInstance();

        concertController.getSubscriptionConcerts(this);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_concert_nearest, container, false);
    }

    public void concertsFoundViewUpdate(ArrayList<UserConcertLink> links)
    {
        if (links.size() > 0) {
            Log.d(TAG, "Concerts found");

            // Load list fragment
            UserConcertLinkListFragment fragment = new UserConcertLinkListFragment(links);
            FragmentService.replaceFragment(this, R.id.fragment_concert_nearest__container, fragment);
        }
    }
}
