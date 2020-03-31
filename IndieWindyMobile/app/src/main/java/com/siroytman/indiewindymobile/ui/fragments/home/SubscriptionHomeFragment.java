package com.siroytman.indiewindymobile.ui.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.PostController;
import com.siroytman.indiewindymobile.model.ArtistPostLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.links.ArtistPostLinkListFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class SubscriptionHomeFragment extends Fragment {
    public static final String TAG = "SubscrHomeFragment";
    private static SubscriptionHomeFragment instance;
    private PostController postController;

    private SubscriptionHomeFragment() {
    }

    public static synchronized SubscriptionHomeFragment getInstance() {
        if (instance == null) {
            instance = new SubscriptionHomeFragment();
        }
        return instance;
    }


    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postController = PostController.getInstance();

        postController.getSubscriptionPosts();
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_subscription, container, false);
    }

    public void postsFoundViewUpdate(ArrayList<ArtistPostLink> postLinks)
    {
        if (postLinks.size() > 0) {
            Log.d(TAG, "Posts found");

            // Load list fragment
            ArtistPostLinkListFragment fragment = new ArtistPostLinkListFragment(postLinks);
            FragmentService.replaceFragment(this, R.id.fragment_home_subscription__container, fragment);
        } else {
            FragmentService.clearFragment(this);
        }
    }
}
