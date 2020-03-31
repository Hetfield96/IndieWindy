package com.siroytman.indiewindymobile.ui.fragments.links;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.ArtistPostLink;
import com.siroytman.indiewindymobile.ui.adapter.ArtistPostLinkListAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class ArtistPostLinkListFragment extends ListFragment {
    private List<ArtistPostLink> links;
    private ArtistPostLinkListAdapter adapter;


    public ArtistPostLinkListFragment() {
    }

    public ArtistPostLinkListFragment(List<ArtistPostLink> links) {
        this.links = links;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArtistPostLinkListAdapter(getActivity(), R.layout.post_list_item, links, getChildFragmentManager());
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_list, container, false);
    }
}
