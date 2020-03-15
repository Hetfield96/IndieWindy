package com.siroytman.indiewindymobile.ui.adapter;

import com.siroytman.indiewindymobile.ui.fragments.personal.SongsPersonalFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PersonalPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = "PersonalPagerAdapter";
    public static int NUM_ITEMS = 2;
    private static SongsPersonalFragment songsPersonalFragment;
//    private static SubscriptionConcertFragment subscriptionConcertFragment;
//    private static SavedConcertFragment savedConcertFragment;

    public PersonalPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


//    public static void nearestConcertFragmentUpdate() {
//        songsPersonalFragment.getNearestConcerts();
//    }
//
//    public static void subscriptionConcertFragmentUpdate() {
//        subscriptionConcertFragment.getSubscriptionConcerts();
//    }
//
//    public static void savedConcertFragmentUpdate() {
//        savedConcertFragment.getSavedConcerts();
//    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                songsPersonalFragment = new SongsPersonalFragment();
                return songsPersonalFragment;
            case 1:
                songsPersonalFragment = new SongsPersonalFragment();
                return songsPersonalFragment;
//                subscriptionConcertFragment = new SubscriptionConcertFragment();
//                return subscriptionConcertFragment;
            case 2:
//                savedConcertFragment = new SavedConcertFragment();
//                return savedConcertFragment;
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Songs";
            case 1:
                return "Downloaded";
            case 2:
                return "Artists";
            case 3:
                return "Albums";
            case 4:
                return "Concerts";
        }
        return "Page " + position;
    }

    public static SongsPersonalFragment getSongsPersonalFragment() {
        return songsPersonalFragment;
    }

//    public static SubscriptionConcertFragment getSubscriptionConcertFragment() {
//        return subscriptionConcertFragment;
//    }
//
//    public static SavedConcertFragment getSavedConcertFragment() {
//        return savedConcertFragment;
//    }
}