package com.siroytman.indiewindymobile.ui.adapter;

import com.siroytman.indiewindymobile.ui.fragments.concert.NearestConcertFragment;
import com.siroytman.indiewindymobile.ui.fragments.concert.SavedConcertFragment;
import com.siroytman.indiewindymobile.ui.fragments.concert.SubscriptionConcertFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ConcertPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private static NearestConcertFragment nearestConcertFragment;
    private static SubscriptionConcertFragment subscriptionConcertFragment;
    private static SavedConcertFragment savedConcertFragment;

    public ConcertPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static void nearestConcertFragmentUpdate() {
        nearestConcertFragment.getNearestConcerts();
    }

    public static void subscriptionConcertFragmentUpdate() {
        subscriptionConcertFragment.getSubscriptionConcerts();
    }

    public static void savedConcertFragmentUpdate() {
        savedConcertFragment.getSavedConcerts();
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragments
                nearestConcertFragment = new NearestConcertFragment();
                return nearestConcertFragment;
            case 1: // Fragment # 0 - This will show FirstFragment different title
                subscriptionConcertFragment = new SubscriptionConcertFragment();
                return subscriptionConcertFragment;
            case 2: // Fragment # 1 - This will show SecondFragment
                savedConcertFragment = new SavedConcertFragment();
                return savedConcertFragment;
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Nearest";
            case 1:
                return "By subscription";
            case 2:
                return "Saved";
        }
        return "Page " + position;
    }

}