package com.siroytman.indiewindymobile.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.ui.fragments.concert.ArtistConcertFragment;
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
        switch (position) {
            case 0:
                return NearestConcertFragment.getInstance();
            case 1:
                return SubscriptionConcertFragment.getInstance();
            case 2:
                return SavedConcertFragment.getInstance();
//            case 3:
//                return ArtistConcertFragment.getInstance();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        Context context = AppController.getContext();
//        Drawable myDrawable = context.getDrawable(R.drawable.ic_artist);
//        String title = "DefaultTitle";
        switch (position) {
            case 0:
//                myDrawable = context.getDrawable(R.drawable.ic_artist);
//                title = "MyTitle";
                return context.getString(R.string.concert__nearest_title);
            case 1:
//                myDrawable = context.getDrawable(R.drawable.ic_artist);
//                title = "MyTitle";
                return context.getString(R.string.concert__subscriptions_title);
            case 2:
//                myDrawable = context.getDrawable(R.drawable.ic_check);
//                title = "MyTitle";
                return context.getString(R.string.concert__saved_title);
//            case 3:
//                myDrawable = context.getDrawable(R.drawable.ic_artist);
//                title = "MyTitle";
//                return context.getString(R.string.concert__artist_title);
            default:
                return null;
        }

//        SpannableStringBuilder sb = new SpannableStringBuilder(" ");
//        try {
//            myDrawable.setBounds(5, 5, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
//            ImageSpan span = new ImageSpan(myDrawable, DynamicDrawableSpan.ALIGN_BASELINE);
//            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            return sb;
//        } catch (Exception e) {
//            // TODO: handle exception
//            return null;
//        }
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        //Do NOTHING;
    }

}