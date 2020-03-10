package com.siroytman.indiewindymobile.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.ArtistController;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.UserAlbumLinkListFragment;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ArtistActivity extends AppCompatActivity {
    public static final String TAG = "ArtistActivity";

    private ArtistController artistController;
    private Artist artist;

    private static FragmentManager fragmentManager;

    private ImageView artistPhoto;
    private TextView artistName;
    private TextView artistDescription;

    // TODO same as album

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        if(fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }

        // Get artist from bundle
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            artist = arguments.getParcelable(Artist.class.getSimpleName());
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        artistPhoto = findViewById(R.id.artist_activity__artist_photo);
        artistName = findViewById(R.id.artist_activity__artist_name);
        artistDescription = findViewById(R.id.artist_activity__artist_description);

        artistName.setText(artist.getName());
        artistDescription.setText(artist.getDescription());
        Glide.with(this).load(artist.getImageUrl()).into(artistPhoto);

        artistController = ArtistController.getInstance(this);
        artistController.getArtistAlbums(artist);
    }

    public void albumsFoundViewUpdate(ArrayList<UserAlbumLink> links)
    {
        // Load list fragment
        UserAlbumLinkListFragment fragment = new UserAlbumLinkListFragment(links, artist);
        FragmentService.replaceFragment(fragmentManager, R.id.artist_activity__albums_container, fragment);
    }
}
