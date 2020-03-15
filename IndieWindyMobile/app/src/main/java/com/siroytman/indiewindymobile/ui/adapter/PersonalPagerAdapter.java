package com.siroytman.indiewindymobile.ui.adapter;

import com.siroytman.indiewindymobile.ui.fragments.personal.SongsPersonalFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PersonalPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = "PersonalPagerAdapter";
    public static int NUM_ITEMS = 1;

    public PersonalPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
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
            case 0:
                return SongsPersonalFragment.getInstance();
            case 1:
//                return SongsPersonalFragment.getInstance();
            case 2:
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
        }
        return "Page " + position;
    }

}