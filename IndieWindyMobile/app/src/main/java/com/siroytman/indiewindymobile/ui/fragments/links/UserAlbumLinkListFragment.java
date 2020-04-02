package com.siroytman.indiewindymobile.ui.fragments.links;

import android.content.Intent;
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
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.activity.MoreActivity;
import com.siroytman.indiewindymobile.ui.adapter.UserAlbumLinkListAdapter;
import com.siroytman.indiewindymobile.ui.fragments.search.SearchFragment;

import java.util.ArrayList;
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
    private Boolean cutList;


    public UserAlbumLinkListFragment() {
    }

    public UserAlbumLinkListFragment(List<UserAlbumLink> albumLinks) {
        this(albumLinks, false, false);
    }

    public UserAlbumLinkListFragment(List<UserAlbumLink> albumLinks, Boolean rawList, Boolean cutList) {
        this.albumLinks = albumLinks;
        this.rawList = rawList;
        this.cutList = cutList;
    }

    public UserAlbumLinkListFragment(List<UserAlbumLink> albumLinks, Artist artist) {
        this.albumLinks = albumLinks;
        this.artist = artist;
        this.rawList = true;
        this.cutList = false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<UserAlbumLink> subLinks;
        if(cutList) {
            // Cut to subLinks not to show all found songs
            subLinks = albumLinks.subList(0, Math.min(SearchFragment.MAX_ELEMENTS, albumLinks.size()));
        } else {
            subLinks = albumLinks;
        }

        if (artist != null) {
            adapter = new UserAlbumLinkListAdapter(getActivity(), R.layout.album_list_item, subLinks, artist);
        } else {
            adapter = new UserAlbumLinkListAdapter(getActivity(), R.layout.album_list_item, subLinks);
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
            albumMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "more button clicked");
                    Log.d(TAG, "albumLinks size = " + albumLinks.size());

                    Bundle bundle = new Bundle();
                    ArrayList<UserAlbumLink> arrayList = new ArrayList<>(albumLinks);
                    bundle.putParcelableArrayList("albumLinks", arrayList);
                    Intent intent = new Intent(getContext(), MoreActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        return view;
    }
}
