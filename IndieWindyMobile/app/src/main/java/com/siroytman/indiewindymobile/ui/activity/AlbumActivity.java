package com.siroytman.indiewindymobile.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.textservice.TextInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AlbumController;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.fragments.UserSongLinkListFragment;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {
    public static final String TAG = "AlbumActivity";

    private AlbumController albumController;
    private Album album;

    private ImageView albumPhoto;
    private TextView albumName;
    private TextView albumArtistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        albumController = AlbumController.getInstance(this);

        // Get album
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            album = arguments.getParcelable(Album.class.getSimpleName());
            Artist artist = arguments.getParcelable(Artist.class.getSimpleName());
            album.setArtist(artist);
        }
        else{
            Log.e(TAG, "Error: Arguments are null!");
        }

        albumName = findViewById(R.id.album_name);
        albumArtistName = findViewById(R.id.album_artist_name);
        albumPhoto = findViewById(R.id.album_photo);

        albumName.setText(album.getName());
        // TODO image
        albumArtistName.setText(album.getArtist().getName());

        albumController.getAlbumSongs(album);
    }

    public void songsFoundViewUpdate(ArrayList<UserSongLink> links)
    {
        // Load list fragment
        UserSongLinkListFragment fragment = new UserSongLinkListFragment(links);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.album_song_list_container, fragment).commit();
    }
}
