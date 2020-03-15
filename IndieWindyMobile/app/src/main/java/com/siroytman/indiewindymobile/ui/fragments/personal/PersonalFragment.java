package com.siroytman.indiewindymobile.ui.fragments.personal;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
import com.siroytman.indiewindymobile.controller.AlbumController;
import com.siroytman.indiewindymobile.controller.ArtistController;
import com.siroytman.indiewindymobile.controller.SongController;
import com.siroytman.indiewindymobile.ui.activity.SettingsActivity;
import com.siroytman.indiewindymobile.ui.adapter.PersonalPagerAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PersonalFragment extends Fragment {
    private static final String TAG = "PersonalFragment";

    private SearchView searchView = null;
    private FragmentStatePagerAdapter adapterViewPager;
    private ViewPager pager;

    private SongController songController;
    private ArtistController artistController;
    private AlbumController albumController;

    private int currentPage = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        pager = view.findViewById(R.id.personal_vp_pager);
        pager.setOffscreenPageLimit(PersonalPagerAdapter.NUM_ITEMS);
        adapterViewPager = new PersonalPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapterViewPager);

        TabLayout tabLayout = view.findViewById(R.id.personal_tab_layout);
        tabLayout.setupWithViewPager(pager);

        // Attach the page change listener inside the activity
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                switch (position) {
                    case 0:
                        songController.searchSongsLinked(SongsPersonalFragment.getInstance());
                        break;
//                    case 1:
//                        break;
                    case 1:
                        artistController.searchArtistsLinked(ArtistsPersonalFragment.getInstance());
                        break;
                    case 2:
                        albumController.searchAlbumsLinked(AlbumsPersonalFragment.getInstance());
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

        songController = SongController.getInstance();
        artistController = ArtistController.getInstance();
        albumController = AlbumController.getInstance();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.personal_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.personal_search);
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
                    hideKeyboard();
                    switch (currentPage) {
                        case 0:
                            songController.searchSongsLinked(SongsPersonalFragment.getInstance(), query);
                            return true;
//                        case 1:
//                            return true;
                        case 1:
                            artistController.searchArtistsLinked(ArtistsPersonalFragment.getInstance(), query);
                            return true;
                        case 2:
                            albumController.searchAlbumsLinked(AlbumsPersonalFragment.getInstance(), query);
                            return true;
                        default:
                            return false;
                    }
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }

        MenuItem settingsItem = menu.findItem(R.id.personal_settings);
        settingsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(getContext(), SettingsActivity.class));
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
    
    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
