package com.siroytman.indiewindymobile.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.PostController;
import com.siroytman.indiewindymobile.ui.adapter.HomePagerAdapter;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private PostController postController;
    private SearchView searchView = null;
    private FragmentStatePagerAdapter adapterViewPager;
    private ViewPager pager;

    private int currentPage = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pager = view.findViewById(R.id.home_vp_pager);
        pager.setOffscreenPageLimit(HomePagerAdapter.NUM_ITEMS);
        adapterViewPager = new HomePagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapterViewPager);
        TabLayout tabLayout = view.findViewById(R.id.home_tab_layout);
        tabLayout.setupWithViewPager(pager);

        // Attach the page change listener inside the activity
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                switch (position) {
                    case 0:
                        postController.getSubscriptionPosts();
                        break;
                    case 1:
                        postController.getLatestPosts();
                        break;
                    default:
                        break;
                }
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        postController = PostController.getInstance();
    }
}

