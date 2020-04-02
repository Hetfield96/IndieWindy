package com.siroytman.indiewindymobile.ui.fragments.concert;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.tabs.TabLayout;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.ConcertController;
import com.siroytman.indiewindymobile.services.KeyboardService;
import com.siroytman.indiewindymobile.ui.adapter.ConcertPagerAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ConcertFragment extends Fragment {
    private static final String TAG = "ConcertFragment";

    private ConcertController concertController;
    private SearchView searchView = null;
    private FragmentStatePagerAdapter adapterViewPager;
    private ViewPager pager;

    private int currentPage = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concert, container, false);

        pager = view.findViewById(R.id.concert_vp_pager);
        pager.setOffscreenPageLimit(ConcertPagerAdapter.NUM_ITEMS);
        adapterViewPager = new ConcertPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapterViewPager);
        TabLayout tabLayout = view.findViewById(R.id.concert_tab_layout);
        tabLayout.setupWithViewPager(pager);

        // Attach the page change listener inside the activity
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                switch (position) {
                    case 0:
                        concertController.getNearestConcerts();
                        break;
                    case 1:
                        concertController.getSubscriptionConcerts();
                        break;
                    case 2:
                        concertController.getSavedConcerts();
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
        setHasOptionsMenu(true);

        concertController = ConcertController.getInstance();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    KeyboardService.hideKeyboard(getActivity());
                    switch (currentPage) {
                        case 0:
                            concertController.getNearestConcerts(query);
                            return true;
                        case 1:
                            concertController.getSubscriptionConcerts(query);
                            return true;
                        case 2:
                            concertController.getSavedConcerts(query);
                            return true;
                        default:
                            return false;
                    }
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

}
