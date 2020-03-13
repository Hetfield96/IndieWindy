package com.siroytman.indiewindymobile.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.UserConcertLink;
import com.siroytman.indiewindymobile.ui.adapter.UserConcertLinkListAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class UserConcertLinkListFragment extends ListFragment {
    private List<UserConcertLink> concertLinks;
    private UserConcertLinkListAdapter adapter;


    public UserConcertLinkListFragment() {
    }

    public UserConcertLinkListFragment(List<UserConcertLink> concertLinks) {
        this.concertLinks = concertLinks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new UserConcertLinkListAdapter(getActivity(), R.layout.concert_list_item, concertLinks);
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_concert_list, container, false);
    }
}
