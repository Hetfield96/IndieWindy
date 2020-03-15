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
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.ui.adapter.UserArtistLinkListAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class UserArtistLinkListFragment extends ListFragment {
    public static final String TAG = "UserArtistLinkListFrag";

    private List<UserArtistLink> artistLinks;
    private UserArtistLinkListAdapter adapter;
    private Boolean rawList;


    public UserArtistLinkListFragment() {
    }

    public UserArtistLinkListFragment(List<UserArtistLink> artistLinks) {
        this(artistLinks, false);
    }

    public UserArtistLinkListFragment(List<UserArtistLink> artistLinks, Boolean rawList) {
        this.artistLinks = artistLinks;
        this.rawList = rawList;
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
        View view = inflater.inflate(R.layout.fragment_artist_list, container, false);

        Button artistMoreButton = view.findViewById(R.id.fragment_artist_list__more);
        if (rawList) {
            artistMoreButton.setVisibility(View.GONE);
            TextView artistTitleView = view.findViewById(R.id.fragment_artist_list__title);
            artistTitleView.setVisibility(View.GONE);
        }
        else {
            // TODO open fragment with full list
            artistMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "more button clicked");
                }
            });
        }
        return view;
    }
}
