package com.siroytman.indiewindymobile.ui.adapter;

import android.os.Parcelable;

import com.siroytman.indiewindymobile.ui.fragments.home.LatestHomeFragment;
import com.siroytman.indiewindymobile.ui.fragments.home.SubscriptionHomeFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = "HomePagerAdapter";
    public static int NUM_ITEMS = 2;

    public HomePagerAdapter(FragmentManager fragmentManager) {
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
                return SubscriptionHomeFragment.getInstance();
            case 1:
                return LatestHomeFragment.getInstance();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Subscriptions";
            case 1:
                return "Latest";
        }
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        //Do NOTHING;
    }

}