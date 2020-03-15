package com.siroytman.indiewindymobile.ui.fragments.links;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.adapter.UserAlbumLinkListAdapter;
import com.siroytman.indiewindymobile.ui.adapter.UserSongLinkListAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class UserAlbumLinkListFragment extends ListFragment {
    private List<UserAlbumLink> albumLinks;
    private UserAlbumLinkListAdapter adapter;
    private Artist artist;


    public UserAlbumLinkListFragment() {
    }

    public UserAlbumLinkListFragment(List<UserAlbumLink> albumLinks) {
        this.albumLinks = albumLinks;
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
        return inflater.inflate(R.layout.fragment_album_list, container, false);
    }
}
