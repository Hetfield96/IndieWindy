package com.siroytman.indiewindymobile.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.ui.adapter.UserArtistLinkListAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class UserArtistLinkListFragment extends ListFragment {
    private List<UserArtistLink> artistLinks;
    private UserArtistLinkListAdapter adapter;
    private Artist artist;


    public UserArtistLinkListFragment() {
    }

    public UserArtistLinkListFragment(List<UserArtistLink> artistLinks) {
        this.artistLinks = artistLinks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new UserArtistLinkListAdapter(getActivity(), R.layout.artist_list_item, artistLinks);
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_list, container, false);
    }
}
