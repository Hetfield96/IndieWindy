package com.siroytman.indiewindymobile.ui.fragments.links;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.ui.adapter.UserAlbumLinkListAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class UserAlbumLinkListFragment extends ListFragment {
    public static final String TAG = "UserAlbumLinkListFrag";
    private List<UserAlbumLink> albumLinks;
    private UserAlbumLinkListAdapter adapter;
    private Artist artist;
    private Boolean rawList;


    public UserAlbumLinkListFragment() {
    }

    public UserAlbumLinkListFragment(List<UserAlbumLink> albumLinks) {
        this(albumLinks, false);
    }

    public UserAlbumLinkListFragment(List<UserAlbumLink> albumLinks, Boolean rawList) {
        this.albumLinks = albumLinks;
        this.rawList = rawList;
    }

    public UserAlbumLinkListFragment(List<UserAlbumLink> albumLinks, Artist artist) {
        this.albumLinks = albumLinks;
        this.artist = artist;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (artist != null) {
            adapter = new UserAlbumLinkListAdapter(getActivity(), R.layout.album_list_item, albumLinks, artist);
        } else {
            adapter = new UserAlbumLinkListAdapter(getActivity(), R.layout.album_list_item, albumLinks);
        }
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_list, container, false);

        Button albumMoreButton = view.findViewById(R.id.fragment_song_album__more);
        if (rawList) {
            albumMoreButton.setVisibility(View.GONE);
            TextView artistTitleView = view.findViewById(R.id.fragment_album_list__title);
            artistTitleView.setVisibility(View.GONE);
        }
        else {
            // TODO open fragment with full list
            albumMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "more button clicked");
                }
            });
        }
        return view;
    }
}
