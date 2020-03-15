package com.siroytman.indiewindymobile.ui.adapter;

import android.os.Parcelable;
import android.util.Log;

import com.siroytman.indiewindymobile.ui.fragments.concert.NearestConcertFragment;
import com.siroytman.indiewindymobile.ui.fragments.concert.SavedConcertFragment;
import com.siroytman.indiewindymobile.ui.fragments.concert.SubscriptionConcertFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ConcertPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = "ConcertPagerAdapter";
    public static int NUM_ITEMS = 3;

    public ConcertPagerAdapter(FragmentManager fragmentManager) {
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
        Log.d(TAG, "Position = " + position);
        switch (position) {
            case 0:
                return NearestConcertFragment.getInstance();
            case 1:
                return SubscriptionConcertFragment.getInstance();
            case 2:
                return SavedConcertFragment.getInstance();
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
                return "Subscriptions";
            case 2:
                return "Saved";
        }
        return "Page " + position;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        //Do NOTHING;
    }

}