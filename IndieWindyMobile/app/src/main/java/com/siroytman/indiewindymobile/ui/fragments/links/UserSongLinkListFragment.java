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
import com.siroytman.indiewindymobile.ui.activity.MoreActivity;
import com.siroytman.indiewindymobile.ui.adapter.UserSongLinkListAdapter;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.fragments.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class UserSongLinkListFragment extends ListFragment {
    public static final String TAG = "UserSongLinkListFrag";

    ArrayList<UserSongLink> songLinks;
    private UserSongLinkListAdapter adapter;
    private Boolean rawList;
    private boolean cutList;


    public UserSongLinkListFragment() {
    }

    public UserSongLinkListFragment(ArrayList<UserSongLink> songLinks) {
        this(songLinks, false, false);
    }

    public UserSongLinkListFragment(ArrayList<UserSongLink> songLinks, Boolean rawList, Boolean cutList) {
        this.songLinks = songLinks;
        this.rawList = rawList;
        this.cutList = cutList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<UserSongLink> subLinks;
        if(cutList) {
            // Cut to subLinks not to show all found songs
            subLinks = new ArrayList<>(songLinks.subList(0, Math.min(SearchFragment.MAX_ELEMENTS, songLinks.size())));
        } else {
            subLinks = songLinks;
        }
        adapter = new UserSongLinkListAdapter(getActivity(), R.layout.song_list_item, subLinks);
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_list, container, false);

        Button songMoreButton = view.findViewById(R.id.fragment_song_list__more);
        if (rawList) {
            songMoreButton.setVisibility(View.GONE);
            TextView songTitleView = view.findViewById(R.id.fragment_song_list__title);
            songTitleView.setVisibility(View.GONE);
        }
        else {
            songMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "more button clicked");
                    Log.d(TAG, "songLinks size = " + songLinks.size());

                    Bundle bundle = new Bundle();
                    ArrayList<UserSongLink> arrayList = new ArrayList<>(songLinks);
                    bundle.putParcelableArrayList("songLinks", arrayList);
                    Intent intent = new Intent(getContext(), MoreActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        return view;
    }
}
