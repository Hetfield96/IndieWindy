package com.siroytman.indiewindymobile.activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.adapter.UserSongLinkListAdapter;
import com.siroytman.indiewindymobile.model.UserSongLink;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class UserSongLinkListFragment extends ListFragment {
    List<UserSongLink> songLinks;
    private UserSongLinkListAdapter adapter;


    public UserSongLinkListFragment() {
    }

    public UserSongLinkListFragment(List<UserSongLink> songLinks) {
        this.songLinks = songLinks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new UserSongLinkListAdapter(getActivity(), R.layout.song_list_item, songLinks);
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.song_list_fragment, container, false);

        return view;
    }
}
