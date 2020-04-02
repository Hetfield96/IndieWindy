package com.siroytman.indiewindymobile.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.ArtistController;
import com.siroytman.indiewindymobile.interfaces.ILinkAdd;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.services.IconChanger;
import com.siroytman.indiewindymobile.ui.fragments.links.UserAlbumLinkListFragment;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ArtistActivity extends AppCompatActivity implements ILinkAdd<Artist> {
    public static final String TAG = "ArtistActivity";


    @SuppressLint("StaticFieldLeak")
    private static ArtistActivity activity;

    private ArtistController artistController;
    private Artist artist;
    private Boolean linkExist = false;

    private ImageView artistPhoto;
    private TextView artistName;
    private TextView artistDescription;
    private ImageView artistAddButton;
    private ImageView artistDonateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        activity = this;

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
        artistAddButton = findViewById(R.id.artist_activity__artist_add_button);
        artistDonateButton = findViewById(R.id.artist_activity__artist_donate_button);

        artistName.setText(artist.getName());
        artistDescription.setText(artist.getDescription());
        Glide.with(activity).load(artist.getImageUrl()).into(artistPhoto);

        artistController = ArtistController.getInstance();
        artistController.getArtistAlbums(activity, artist);
        artistController.linkExist(activity);


        artistAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkExist) {
                    artistController.removeUserArtistLink(activity);
                } else {
                    artistController.addUserArtistLink(activity);
                }
            }
        });

        artistDonateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtistActivity.this, DonationActivity.class);
                intent.putExtra(Artist.class.getSimpleName(), artist);
                startActivity(intent);
            }
        });
    }

    public void albumsFoundViewUpdate(ArrayList<UserAlbumLink> links)
    {
        // Load list fragment
        UserAlbumLinkListFragment fragment = new UserAlbumLinkListFragment(links, artist);
        FragmentService.replaceFragment(activity, R.id.artist_activity__albums_container, fragment);
    }

    @Override
    public Artist getItem() {
        return artist;
    }

    @Override
    public void removed() {
        IconChanger.setIconAdd(artistAddButton);
        linkExist = false;
    }

    @Override
    public void added() {
        IconChanger.setIconCheck(artistAddButton);
        linkExist = true;
    }

}
