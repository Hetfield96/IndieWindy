package com.siroytman.indiewindymobile.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AlbumController;
import com.siroytman.indiewindymobile.interfaces.ILinkActions;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.UserSongLinkListFragment;

import java.util.ArrayList;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class AlbumActivity extends FragmentActivity implements ILinkActions<Album> {
    public static final String TAG = "AlbumActivity";

    private static FragmentManager fragmentManager;

    private AlbumController albumController;
    private Album album;

    private ImageView albumPhoto;
    private TextView albumName;
    private TextView albumArtistName;
    private ImageView albumAddButton;
    private ImageView albumOptionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        if(fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }

        // Get album from bundle
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            album = arguments.getParcelable(Album.class.getSimpleName());
            Artist artist = arguments.getParcelable(Artist.class.getSimpleName());
            album.setArtist(artist);
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        albumName = findViewById(R.id.album_activity__album_name);
        albumArtistName = findViewById(R.id.album_activity__album_artist_name);
        albumPhoto = findViewById(R.id.album_activity__album_photo);
        albumAddButton = findViewById(R.id.album_activity__album_add_button);
        albumOptionsButton = findViewById(R.id.album_activity__album_options_button);

        albumName.setText(album.getName());
        albumArtistName.setText(album.getArtist().getName());
        Glide.with(this).load(album.getImageUrl()).into(albumPhoto);

        // TODO set addButtonState - write methon on back to get link


        albumController = AlbumController.getInstance();
        albumController.getAlbumSongs(this);

        final AlbumActivity activity = this;
        albumAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO according to addButtonState
                albumController.addUserAlbumLink(activity);
            }
        });

        albumOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
    }

    public void songsFoundViewUpdate(ArrayList<UserSongLink> links)
    {
        // Load list fragment
        UserSongLinkListFragment fragment = new UserSongLinkListFragment(links);
        FragmentService.replaceFragment(fragmentManager, R.id.album_activity__album_song_list_container, fragment);
    }


    @Override
    public Album getItem() {
        return album;
    }

    @Override
    public void removed() {
        albumSetIconAdd();
    }

    @Override
    public void added() {
        albumSetIconCheck();
    }

    private void albumSetIconCheck() {
        albumAddButton.setImageResource(R.drawable.ic_check);
    }

    private void albumSetIconAdd() {
        albumAddButton.setImageResource(R.drawable.ic_add);
    }

    // TODO make showPopupMenu public here
}
