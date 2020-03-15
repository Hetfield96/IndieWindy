package com.siroytman.indiewindymobile.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.ArtistController;
import com.siroytman.indiewindymobile.interfaces.ILinkActions;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.links.UserAlbumLinkListFragment;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ArtistActivity extends AppCompatActivity implements ILinkActions<Artist> {
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
    private ImageView artistOptionsButton;


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
        artistOptionsButton = findViewById(R.id.artist_activity__artist_options_button);

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

        // TODO what is in options button?
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
        artistSetAddButtonIconAdd();
        linkExist = false;
    }

    @Override
    public void added() {
        artistSetAddButtonIconCheck();
        linkExist = true;
    }

    private void artistSetAddButtonIconCheck() {
        artistAddButton.setImageResource(R.drawable.ic_check);
    }

    private void artistSetAddButtonIconAdd() {
        artistAddButton.setImageResource(R.drawable.ic_add);
    }
}
