package com.siroytman.indiewindymobile.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.ui.adapter.UserSongLinkListAdapter;
import com.siroytman.indiewindymobile.model.UserSongLink;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class UserSongLinkListFragment extends ListFragment {
    public static final String TAG = "UserSongLinkListFrag";

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
        View view = inflater.inflate(R.layout.fragment_song_list, container, false);

        // TODO open fragment with full list
        Button songMoreButton = view.findViewById(R.id.fragment_song_list__more);
        songMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "more button clicked");
            }
        });

        return view;
    }
}
