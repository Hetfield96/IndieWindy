package com.siroytman.indiewindymobile.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AlbumController;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.fragments.UserSongLinkListFragment;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class AlbumActivity extends FragmentActivity {
    public static final String TAG = "AlbumActivity";

    private static FragmentManager fragmentManager;

    private AlbumController albumController;
    private Album album;

    private ImageView albumPhoto;
    private TextView albumName;
    private TextView albumArtistName;

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

        albumName = findViewById(R.id.album_name);
        albumArtistName = findViewById(R.id.album_artist_name);
        albumPhoto = findViewById(R.id.album_photo);

        albumName.setText(album.getName());
        albumArtistName.setText(album.getArtist().getName());
        Glide.with(this).load(album.getImageUrl()).into(albumPhoto);


        albumController = AlbumController.getInstance(this);
        albumController.getAlbumSongs(album);
    }

    public void songsFoundViewUpdate(ArrayList<UserSongLink> links)
    {
        // Load list fragment
        UserSongLinkListFragment fragment = new UserSongLinkListFragment(links);
        FragmentService.replaceFragment(fragmentManager, R.id.album_song_list_container, fragment);
    }
}
